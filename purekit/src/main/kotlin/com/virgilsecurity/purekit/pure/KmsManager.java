package com.virgilsecurity.purekit.pure;

import com.google.protobuf.ByteString;
import com.virgilsecurity.crypto.phe.*;
import com.virgilsecurity.purekit.data.ProtocolException;
import com.virgilsecurity.purekit.data.ProtocolHttpException;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client;
import com.virgilsecurity.purekit.pure.client.HttpKmsClient;
import com.virgilsecurity.purekit.pure.exception.KmsClientException;
import com.virgilsecurity.purekit.pure.exception.PureCryptoException;
import com.virgilsecurity.purekit.pure.exception.PureException;
import com.virgilsecurity.purekit.pure.model.UserRecord;
import com.virgilsecurity.purekit.utils.ValidateUtils;

class KmsManager {
    public static final String RECOVER_PWD_ALIAS = "RECOVERY_PASSWORD";

    private final int currentVersion;
    private final PureCrypto pureCrypto;
    private final UokmsClient currentClient;
    private final UokmsClient previousClient;
    private final HttpKmsClient httpClient;
    private final UokmsWrapRotation kmsRotation;

    public KmsManager(PureContext context) throws PureCryptoException {
        try {
            this.pureCrypto = new PureCrypto(context.getCrypto());
            this.currentClient = new UokmsClient();
            this.currentClient.setOperationRandom(context.getCrypto().getRng());
            this.currentClient.setRandom(context.getCrypto().getRng());

            if (context.getUpdateToken() != null) {
                this.currentVersion = context.getPublicKey().getVersion() + 1;
                byte[] updateToken = context.getUpdateToken().getPayload2();
                this.kmsRotation = new UokmsWrapRotation();
                this.kmsRotation.setOperationRandom(context.getCrypto().getRng());
                this.kmsRotation.setUpdateToken(updateToken);
                this.previousClient = new UokmsClient();
                this.previousClient.setOperationRandom(context.getCrypto().getRng());
                this.previousClient.setRandom(context.getCrypto().getRng());
                this.previousClient.setKeys(context.getSecretKey().getPayload2(),
                        context.getPublicKey().getPayload2());

                UokmsClientRotateKeysResult rotateKeysResult = this.previousClient.rotateKeys(context.getUpdateToken().getPayload2());
                this.currentClient.setKeys(rotateKeysResult.getNewClientPrivateKey(), rotateKeysResult.getNewServerPublicKey());
            } else {
                this.currentVersion = context.getPublicKey().getVersion();
                this.kmsRotation = null;
                this.previousClient = null;
                this.currentClient.setKeys(context.getSecretKey().getPayload2(),
                        context.getPublicKey().getPayload2());
            }

            this.httpClient = context.getKmsClient();
        }
        catch (PheException e) {
            throw new PureCryptoException(e);
        }
    }

    private UokmsClient getKmsClient(int kmsVersion) throws NullPointerException {
        if (this.currentVersion == kmsVersion) {
            return this.currentClient;
        } else if (this.currentVersion == kmsVersion + 1) {
            return this.previousClient;
        } else {
            throw new NullPointerException("kmsClient");
        }
    }

    private byte[] recoverSecret(UserRecord userRecord) throws PureException {
        try {
            UokmsClient kmsClient = getKmsClient(userRecord.getRecordVersion());

            UokmsClientGenerateDecryptRequestResult uokmsClientGenerateDecryptRequestResult = kmsClient.generateDecryptRequest(userRecord.getPasswordRecoveryWrap());

            PurekitProtosV3Client.DecryptRequest decryptRequest = PurekitProtosV3Client.DecryptRequest.newBuilder()
                    .setVersion(userRecord.getRecordVersion())
                    .setAlias(RECOVER_PWD_ALIAS)
                    .setRequest(ByteString.copyFrom(uokmsClientGenerateDecryptRequestResult.getDecryptRequest()))
                    .build();

            PurekitProtosV3Client.DecryptResponse decryptResponse = httpClient.decrypt(decryptRequest);

            return kmsClient.processDecryptResponse(userRecord.getPasswordRecoveryWrap(),
                    uokmsClientGenerateDecryptRequestResult.getDecryptRequest(),
                    decryptResponse.getResponse().toByteArray(),
                    uokmsClientGenerateDecryptRequestResult.getDeblindFactor(),
                    PureCrypto.DERIVED_SECRET_LENGTH);
        }
        catch (PheException e) {
            throw new PureCryptoException(e);
        } catch (ProtocolException e) {
            throw new KmsClientException(e);
        } catch (ProtocolHttpException e) {
            throw new KmsClientException(e);
        }
    }

    byte[] performRotation(byte[] wrap) throws PureCryptoException {
        try {
            ValidateUtils.checkNull(kmsRotation, "kmsUpdateToken");
            ValidateUtils.checkNull(wrap, "wrap");

            return kmsRotation.updateWrap(wrap);
        }
        catch (PheException e) {
            throw new PureCryptoException(e);
        }
    }

    static class PwdRecoveryData {
        private final byte[] wrap;
        private final byte[] blob;

        public PwdRecoveryData(byte[] wrap, byte[] blob) {
            ValidateUtils.checkNull(wrap, "wrap");
            ValidateUtils.checkNull(blob, "blob");

            this.wrap = wrap;
            this.blob = blob;
        }

        public byte[] getWrap() {
            return wrap;
        }

        public byte[] getBlob() {
            return blob;
        }
    }

    byte[] recoverPwd(UserRecord userRecord) throws PureException {
        byte[] derivedSecret = recoverSecret(userRecord);

        return pureCrypto.decryptSymmetricOneTimeKey(userRecord.getPasswordRecoveryBlob(), new byte[0], derivedSecret);
    }

    PwdRecoveryData generatePwdRecoveryData(byte[] passwordHash) throws PureCryptoException {
        try {
            UokmsClientGenerateEncryptWrapResult kmsResult = currentClient.generateEncryptWrap(PureCrypto.DERIVED_SECRET_LENGTH);

            byte[] derivedSecret = kmsResult.getEncryptionKey();

            byte[] resetPwdBlob = pureCrypto.encryptSymmetricOneTimeKey(passwordHash, new byte[0], derivedSecret);

            return new PwdRecoveryData(kmsResult.getWrap(), resetPwdBlob);
        }
        catch (PheException e) {
            throw new PureCryptoException(e);
        }
    }
}
