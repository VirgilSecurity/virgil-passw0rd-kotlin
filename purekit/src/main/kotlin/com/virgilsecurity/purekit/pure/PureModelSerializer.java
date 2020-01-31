package com.virgilsecurity.purekit.pure;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Crypto;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Storage;
import com.virgilsecurity.purekit.pure.exception.PureLogicException;
import com.virgilsecurity.purekit.pure.model.*;
import com.virgilsecurity.purekit.utils.ValidateUtils;
import com.virgilsecurity.sdk.crypto.VirgilCrypto;
import com.virgilsecurity.sdk.crypto.VirgilKeyPair;
import com.virgilsecurity.sdk.crypto.exceptions.SigningException;
import com.virgilsecurity.sdk.crypto.exceptions.VerificationException;

import java.util.Date;

public class PureModelSerializer {
    private static final int CURRENT_USER_VERSION = 1;
    private static final int CURRENT_USER_SIGNED_VERSION = 1;
    private static final int CURRENT_CELL_KEY_VERSION = 1;
    private static final int CURRENT_CELL_KEY_SIGNED_VERSION = 1;
    private static final int CURRENT_ROLE_VERSION = 1;
    private static final int CURRENT_ROLE_SIGNED_VERSION = 1;
    private static final int CURRENT_ROLE_ASSIGNMENT_VERSION = 1;
    private static final int CURRENT_ROLE_ASSIGNMENT_SIGNED_VERSION = 1;
    private static final int CURRENT_GRANT_KEY_VERSION = 1;
    private static final int CURRENT_GRANT_KEY_SIGNED_VERSION = 1;


    private final VirgilCrypto crypto;
    private final VirgilKeyPair signingKey;

    /**
     * Instantiates PureModelSerializer.
     *
     * @param crypto VirgilCrypto
     * @param signingKey Key used to sign data before sending to Virgil.
     */
    public PureModelSerializer(VirgilCrypto crypto, VirgilKeyPair signingKey) {
        ValidateUtils.checkNull(crypto, "crypto");
        ValidateUtils.checkNull(signingKey, "signingKey");

        this.crypto = crypto;
        this.signingKey = signingKey;
    }

    public PurekitProtosV3Storage.UserRecord serializeUserRecord(UserRecord userRecord) throws SigningException, InvalidProtocolBufferException {
        PurekitProtosV3Crypto.EnrollmentRecord enrollmentRecord =
                PurekitProtosV3Crypto.EnrollmentRecord.parseFrom(userRecord.getPheRecord());

        byte[] userRecordSigned = PurekitProtosV3Storage.UserRecordSigned
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_USER_SIGNED_VERSION)
                .setUserId(userRecord.getUserId())
                .setPheRecordNc(enrollmentRecord.getNc())
                .setPheRecordNs(enrollmentRecord.getNs())
                .setUpk(ByteString.copyFrom(userRecord.getUpk()))
                .setEncryptedUsk(ByteString.copyFrom(userRecord.getEncryptedUsk()))
                .setEncryptedUskBackup(ByteString.copyFrom(userRecord.getEncryptedUskBackup()))
                .setBackupPwdHash(ByteString.copyFrom(userRecord.getBackupPwdHash()))
                .setPasswordRecoveryBlob(ByteString.copyFrom(userRecord.getPasswordRecoveryBlob()))
                .build()
                .toByteArray();

        byte[] signature = crypto.generateSignature(userRecordSigned,
                this.signingKey.getPrivateKey());

        return PurekitProtosV3Storage.UserRecord
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_USER_VERSION)
                .setUserRecordSigned(ByteString.copyFrom(userRecordSigned))
                .setSignature(ByteString.copyFrom(signature))
                .setPheRecordT0(enrollmentRecord.getT0())
                .setPheRecordT1(enrollmentRecord.getT1())
                .setRecordVersion(userRecord.getRecordVersion())
                .setPasswordRecoveryWrap(ByteString.copyFrom(userRecord.getPasswordRecoveryWrap()))
                .build();
    }

    public UserRecord parseUserRecord(PurekitProtosV3Storage.UserRecord protobufRecord)
            throws PureLogicException, VerificationException, InvalidProtocolBufferException {

        boolean verified = crypto.verifySignature(protobufRecord.getSignature().toByteArray(),
                protobufRecord.getUserRecordSigned()
                        .toByteArray(),
                this.signingKey.getPublicKey());

        if (!verified) {
            throw new PureLogicException(
                    PureLogicException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.UserRecordSigned recordSigned =
                PurekitProtosV3Storage.UserRecordSigned.parseFrom(protobufRecord.getUserRecordSigned());

        byte[] pheRecord = PurekitProtosV3Crypto.EnrollmentRecord
                .newBuilder()
                .setNc(recordSigned.getPheRecordNc())
                .setNs(recordSigned.getPheRecordNs())
                .setT0(protobufRecord.getPheRecordT0())
                .setT1(protobufRecord.getPheRecordT1())
                .build()
                .toByteArray();

        return new UserRecord(recordSigned.getUserId(),
                pheRecord,
                protobufRecord.getRecordVersion(),
                recordSigned.getUpk().toByteArray(),
                recordSigned.getEncryptedUsk().toByteArray(),
                recordSigned.getEncryptedUskBackup().toByteArray(),
                recordSigned.getBackupPwdHash().toByteArray(),
                protobufRecord.getPasswordRecoveryWrap().toByteArray(),
                recordSigned.getPasswordRecoveryBlob().toByteArray());
    }

    public PurekitProtosV3Storage.CellKey serializeCellKey(CellKey cellKey) throws SigningException {
        byte[] cellKeySigned = PurekitProtosV3Storage.CellKeySigned
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_CELL_KEY_SIGNED_VERSION)
                .setUserId(cellKey.getUserId())
                .setDataId(cellKey.getDataId())
                .setCpk(ByteString.copyFrom(cellKey.getCpk()))
                .setEncryptedCskCms(ByteString.copyFrom(cellKey.getEncryptedCskCms()))
                .setEncryptedCskBody(ByteString.copyFrom(cellKey.getEncryptedCskBody()))
                .build()
                .toByteArray();

        byte[] signature = crypto.generateSignature(cellKeySigned, this.signingKey.getPrivateKey());

        return PurekitProtosV3Storage.CellKey
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_CELL_KEY_VERSION)
                .setCellKeySigned(ByteString.copyFrom(cellKeySigned))
                .setSignature(ByteString.copyFrom(signature))
                .build();
    }

    public CellKey parseCellKey(PurekitProtosV3Storage.CellKey protobufRecord)
            throws PureLogicException, VerificationException, InvalidProtocolBufferException {
        boolean verified = crypto.verifySignature(
                protobufRecord.getSignature().toByteArray(),
                protobufRecord.getCellKeySigned().toByteArray(),
                this.signingKey.getPublicKey()
        );

        if (!verified) {
            throw new PureLogicException(
                    PureLogicException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.CellKeySigned keySigned =
                PurekitProtosV3Storage.CellKeySigned.parseFrom(protobufRecord.getCellKeySigned());

        return new CellKey(keySigned.getUserId(), keySigned.getDataId(),
                keySigned.getCpk().toByteArray(),
                keySigned.getEncryptedCskCms().toByteArray(),
                keySigned.getEncryptedCskBody().toByteArray());
    }

    public PurekitProtosV3Storage.Role serializeRole(Role role) throws SigningException {
        byte[] roleSigned = PurekitProtosV3Storage.RoleSigned
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_ROLE_SIGNED_VERSION)
                .setName(role.getRoleName())
                .setRpk(ByteString.copyFrom(role.getRpk()))
                .build()
                .toByteArray();

        byte[] signature = crypto.generateSignature(roleSigned, this.signingKey.getPrivateKey());

        return PurekitProtosV3Storage.Role
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_ROLE_VERSION)
                .setRoleSigned(ByteString.copyFrom(roleSigned))
                .setSignature(ByteString.copyFrom(signature))
                .build();
    }

    public Role parseRole(PurekitProtosV3Storage.Role protobufRecord)
            throws PureLogicException, VerificationException, InvalidProtocolBufferException {
        boolean verified = crypto.verifySignature(
                protobufRecord.getSignature().toByteArray(),
                protobufRecord.getRoleSigned().toByteArray(),
                this.signingKey.getPublicKey()
        );

        if (!verified) {
            throw new PureLogicException(
                    PureLogicException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.RoleSigned roleSigned =
                PurekitProtosV3Storage.RoleSigned.parseFrom(protobufRecord.getRoleSigned());

        return new Role(roleSigned.getName(), roleSigned.getRpk().toByteArray());
    }

    public PurekitProtosV3Storage.RoleAssignment serializeRoleAssignment(RoleAssignment roleAssignment) throws SigningException {
        byte[] roleAssignmentSigned = PurekitProtosV3Storage.RoleAssignmentSigned
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_ROLE_ASSIGNMENT_SIGNED_VERSION)
                .setRoleName(roleAssignment.getRoleName())
                .setUserId(roleAssignment.getUserId())
                .setEncryptedRsk(ByteString.copyFrom(roleAssignment.getEncryptedRsk()))
                .setPublicKeyId(ByteString.copyFrom(roleAssignment.getPublicKeyId()))
                .build()
                .toByteArray();

        byte[] signature = crypto.generateSignature(roleAssignmentSigned, this.signingKey.getPrivateKey());

        return PurekitProtosV3Storage.RoleAssignment
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_ROLE_ASSIGNMENT_VERSION)
                .setRoleAssignmentSigned(ByteString.copyFrom(roleAssignmentSigned))
                .setSignature(ByteString.copyFrom(signature))
                .build();
    }

    public RoleAssignment parseRoleAssignment(PurekitProtosV3Storage.RoleAssignment protobufRecord)
            throws PureLogicException, VerificationException, InvalidProtocolBufferException {
        boolean verified = crypto.verifySignature(
                protobufRecord.getSignature().toByteArray(),
                protobufRecord.getRoleAssignmentSigned().toByteArray(),
                this.signingKey.getPublicKey()
        );

        if (!verified) {
            throw new PureLogicException(
                    PureLogicException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.RoleAssignmentSigned roleAssignmentSigned =
                PurekitProtosV3Storage.RoleAssignmentSigned.parseFrom(protobufRecord.getRoleAssignmentSigned());

        return new RoleAssignment(roleAssignmentSigned.getRoleName(), roleAssignmentSigned.getUserId(),
                roleAssignmentSigned.getPublicKeyId().toByteArray(), roleAssignmentSigned.getEncryptedRsk().toByteArray());
    }

    public PurekitProtosV3Storage.GrantKey serializeGrantKey(GrantKey grantKey) throws SigningException {
        byte[] grantKeySigned = PurekitProtosV3Storage.GrantKeySigned
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_GRANT_KEY_SIGNED_VERSION)
                .setUserId(grantKey.getUserId())
                .setKeyId(ByteString.copyFrom(grantKey.getKeyId()))
                .setEncryptedGrantKey(ByteString.copyFrom(grantKey.getEncryptedGrantKey()))
                .setCreationDate((int) (grantKey.getCreationDate().getTime() / 1000))
                .setExpirationDate((int) (grantKey.getExpirationDate().getTime() / 1000))
                .build()
                .toByteArray();

        byte[] signature = crypto.generateSignature(grantKeySigned, this.signingKey.getPrivateKey());

        return PurekitProtosV3Storage.GrantKey
                .newBuilder()
                .setVersion(PureModelSerializer.CURRENT_GRANT_KEY_VERSION)
                .setGrantKeySigned(ByteString.copyFrom(grantKeySigned))
                .setSignature(ByteString.copyFrom(signature))
                .build();
    }

    public GrantKey parseGrantKey(PurekitProtosV3Storage.GrantKey protobufRecord) throws VerificationException, PureLogicException, InvalidProtocolBufferException {
        boolean verified = crypto.verifySignature(
                protobufRecord.getSignature().toByteArray(),
                protobufRecord.getGrantKeySigned().toByteArray(),
                this.signingKey.getPublicKey()
        );

        if (!verified) {
            throw new PureLogicException(
                    PureLogicException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.GrantKeySigned grantKeySigned =
                PurekitProtosV3Storage.GrantKeySigned.parseFrom(protobufRecord.getGrantKeySigned());

        return new GrantKey(grantKeySigned.getUserId(), grantKeySigned.getKeyId().toByteArray(),
                grantKeySigned.getEncryptedGrantKey().toByteArray(),
                new Date(grantKeySigned.getCreationDate() * 1000),
                new Date(grantKeySigned.getExpirationDate() * 1000));
    }
}
