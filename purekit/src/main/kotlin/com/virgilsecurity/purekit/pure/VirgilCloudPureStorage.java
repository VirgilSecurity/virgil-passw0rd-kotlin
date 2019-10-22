/*
 * Copyright (c) 2015-2019, Virgil Security, Inc.
 *
 * Lead Maintainer: Virgil Security Inc. <support@virgilsecurity.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     (1) Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *
 *     (2) Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *     (3) Neither the name of virgil nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.virgilsecurity.purekit.pure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.virgilsecurity.purekit.data.ProtocolException;
import com.virgilsecurity.purekit.data.ProtocolHttpException;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Crypto;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Storage;
import com.virgilsecurity.purekit.pure.exception.PureException;
import com.virgilsecurity.purekit.pure.exception.ServiceErrorCode;
import com.virgilsecurity.purekit.pure.model.CellKey;
import com.virgilsecurity.purekit.pure.model.UserRecord;
import com.virgilsecurity.purekit.utils.ValidateUtils;
import com.virgilsecurity.sdk.crypto.VirgilCrypto;
import com.virgilsecurity.sdk.crypto.VirgilKeyPair;
import com.virgilsecurity.sdk.crypto.exceptions.SigningException;
import com.virgilsecurity.sdk.crypto.exceptions.VerificationException;

/**
 * PureStorage on Virgil cloud side
 */
public class VirgilCloudPureStorage implements PureStorage {

    private static final int CURRENT_USER_VERSION = 1;
    private static final int CURRENT_USER_SIGNED_VERSION = 1;
    private static final int CURRENT_CELL_KEY_VERSION = 1;
    private static final int CURRENT_CELL_KEY_SIGNED_VERSION = 1;

    private final VirgilCrypto crypto;
    private final HttpPureClient client;
    private final VirgilKeyPair signingKey;

    /**
     * Instantiates VirgilCloudPureStorage.
     *
     * @param signingKey Key used to sign data before sending to Virgil.
     */
    public VirgilCloudPureStorage(VirgilCrypto crypto,
                                  HttpPureClient client,
                                  VirgilKeyPair signingKey) {
        ValidateUtils.checkNull(crypto, "crypto");
        ValidateUtils.checkNull(client, "client");
        ValidateUtils.checkNull(signingKey, "signingKey");

        this.crypto = crypto;
        this.client = client;
        this.signingKey = signingKey;
    }

    /**
     * Insert a new user into a storage.
     *
     * @param userRecord User record.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws InvalidProtocolBufferException If provided UserRecord cannot be parsed as
     * Protobuf message.
     * @throws SigningException Please, see
     * {@link com.virgilsecurity.sdk.crypto.VirgilCrypto#generateSignature} method's doc.
     */
    @Override
    public void insertUser(UserRecord userRecord)
        throws ProtocolException, ProtocolHttpException, InvalidProtocolBufferException,
        SigningException {

        sendUser(userRecord, true);
    }

    /**
     * Updates a user in a storage.
     *
     * @param userRecord User record.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws InvalidProtocolBufferException If provided UserRecord cannot be parsed as
     * Protobuf message.
     * @throws SigningException Please, see
     * {@link com.virgilsecurity.sdk.crypto.VirgilCrypto#generateSignature} method's doc.
     */
    @Override
    public void updateUser(UserRecord userRecord)
        throws ProtocolException, ProtocolHttpException, InvalidProtocolBufferException,
        SigningException {

        sendUser(userRecord, false);
    }

    /**
     * Obtains a user record with the given userId from a storage.
     *
     * @param userId User Id.
     *
     * @return UserRecord.
     *
     * @throws PureException If a user has not been found in a storage or user id mismatches the one
     * from a server. Use {@link PureException#getErrorStatus()} to know the particular case.
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws InvalidProtocolBufferException If a PurekitProtosV3Storage.UserRecord received from
     * a server cannot be parsed as a Protobuf message.
     * @throws VerificationException If signature verification operation failed.
     */
    @Override
    public UserRecord selectUser(String userId)
        throws PureException, ProtocolException, ProtocolHttpException,
        InvalidProtocolBufferException, VerificationException {

        PurekitProtosV3Storage.UserRecord protobufRecord;

        try {
            protobufRecord = client.getUser(userId);
        } catch (ProtocolException exception) {
            if (exception.getErrorCode() == ServiceErrorCode.USER_NOT_FOUND.getCode()) {
                throw new PureException(PureException.ErrorStatus.USER_NOT_FOUND_IN_STORAGE);
            }

            throw exception;
        } catch (ProtocolHttpException exception) {
            throw exception;
        }

        UserRecord userRecord = parse(protobufRecord);

        if (!userRecord.getUserId().equals(userId)) {
            throw new PureException(PureException.ErrorStatus.USER_ID_MISMATCH);
        }

        return userRecord;
    }

    /**
     * Obtains a users record with the given userId from a storage.
     *
     * @param userIds User Ids.
     *
     * @return UserRecords.
     *
     * @throws PureException If user Id duplicate has been found or user id mismatches the one
     * from a server. Use {@link PureException#getErrorStatus()} to know the particular case.
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws InvalidProtocolBufferException If a PurekitProtosV3Storage.UserRecord received from
     * a server cannot be parsed as a Protobuf message.
     * @throws VerificationException If signature verification operation failed.
     */
    @Override
    public Iterable<UserRecord> selectUsers(Set<String> userIds)
        throws PureException, ProtocolException, ProtocolHttpException,
        InvalidProtocolBufferException, VerificationException {

        HashSet<String> idsSet = new HashSet<>(userIds);

        PurekitProtosV3Storage.UserRecords protoRecords;

        protoRecords = client.getUsers(userIds);

        if (protoRecords.getUserRecordsCount() != userIds.size()) {
            throw new PureException(PureException.ErrorStatus.DUPLICATE_USER_ID);
        }

        ArrayList<UserRecord> userRecords = new ArrayList<>(protoRecords.getUserRecordsCount());

        for (PurekitProtosV3Storage.UserRecord protobufRecord : protoRecords.getUserRecordsList()) {
            UserRecord userRecord = parse(protobufRecord);

            if (!idsSet.contains(userRecord.getUserId())) {
                throw new PureException(PureException.ErrorStatus.USER_ID_MISMATCH);
            }

            idsSet.remove(userRecord.getUserId());

            userRecords.add(userRecord);
        }

        return userRecords;
    }

    /**
     * This method throws UnsupportedOperationException, as in case of using Virgil Cloud storage,
     * rotation happens on Virgil side.
     *
     * @param pheRecordVersion PHE record version.
     *
     * @return always throws UnsupportedOperationException.
     *
     * @throws UnsupportedOperationException always.
     */
    @Override
    public Iterable<UserRecord> selectUsers(int pheRecordVersion) {
        throw new UnsupportedOperationException(
            "This method always throws UnsupportedOperationException, as in case of using "
                + "Virgil Cloud storage, rotation happens on the Virgil side."
        );
    }

    /**
     * Deletes user with the given id.
     *
     * @param userId User Id.
     * @param cascade Deletes all user cell keys if true.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    @Override
    public void deleteUser(String userId,
                           boolean cascade) throws ProtocolException, ProtocolHttpException {
        client.deleteUser(userId, cascade);
    }

    /**
     * Obtains CellKey for given userId and dataId from a storage.
     *
     * @param userId User Id.
     * @param dataId Data Id.
     *
     * @return CellKey.
     *
     * @throws PureException If cell key has not been found or if storage signature verification has
     * been failed or user id mismatches the one from a server.
     * Use {@link PureException#getErrorStatus()} to know the particular case.
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws VerificationException If signature verification operation failed.
     * @throws InvalidProtocolBufferException If a CellKey received from a server cannot be parsed
     * as a Protobuf message.
     */
    @Override
    public CellKey selectKey(String userId, String dataId)
        throws PureException, ProtocolException, ProtocolHttpException, VerificationException,
        InvalidProtocolBufferException {

        PurekitProtosV3Storage.CellKey protobufRecord;
        try {
            protobufRecord = client.getCellKey(userId, dataId);
        } catch (ProtocolException exception) {
            if (exception.getErrorCode() == ServiceErrorCode.CELL_KEY_NOT_FOUND.getCode()) {
                return null;
            }

            throw exception;
        } catch (ProtocolHttpException exception) {
            throw exception;
        }

        boolean verified = crypto.verifySignature(
                protobufRecord.getSignature().toByteArray(),
                protobufRecord.getCellKeySigned().toByteArray(),
                this.signingKey.getPublicKey()
            );

        if (!verified) {
            throw new PureException(
                PureException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
            );
        }

        PurekitProtosV3Storage.CellKeySigned keySigned =
                PurekitProtosV3Storage.CellKeySigned.parseFrom(protobufRecord.getCellKeySigned());

        CellKey cellKey = new CellKey(keySigned.getCpk().toByteArray(),
                                      keySigned.getEncryptedCskCms().toByteArray(),
                                      keySigned.getEncryptedCskBody().toByteArray());

        if (!userId.equals(keySigned.getUserId()) || !dataId.equals(keySigned.getDataId())) {
            throw new PureException(PureException.ErrorStatus.USER_ID_MISMATCH);
        }

        return cellKey;
    }

    /**
     * Insert CellKey key into a storage.
     *
     * @implSpec this method MUST throw {@link PureException} with
     * {@link PureException.ErrorStatus#CELL_KEY_ALREADY_EXISTS_IN_STORAGE} if key with given
     * userId and dataId already exists.
     *
     * @param userId User Id.
     * @param dataId Data Id.
     * @param cellKey Cell key record.
     *
     * @throws PureException If a cell key already exists in a storage.
     * Use {@link PureException#getErrorStatus()} to know the particular case.
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws SigningException If crypto sign operation failed.
     */
    @Override
    public void insertKey(String userId, String dataId, CellKey cellKey)
        throws PureException, ProtocolException, ProtocolHttpException, SigningException {

        insertKey(userId, dataId, cellKey, true);
    }

    /**
     * Updates CellKey.
     *
     * @param userId User Id.
     * @param dataId Data Id.
     * @param cellKey Cell key record.
     *
     * @throws PureException If a cell key already exists in a storage.
     * Use {@link PureException#getErrorStatus()} to know the particular case.
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     * @throws SigningException If crypto sign operation failed.
     */
    @Override
    public void updateKey(String userId, String dataId, CellKey cellKey)
        throws PureException, ProtocolException, ProtocolHttpException, SigningException {

        insertKey(userId, dataId, cellKey, false);
    }

    /**
     * Deletes cell key with given userId and dataId.
     *
     * @param userId User Id.
     * @param dataId Data Id.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    @Override
    public void deleteKey(String userId, String dataId)
        throws ProtocolException, ProtocolHttpException {

        client.deleteCellKey(userId, dataId);
    }

    private void sendUser(UserRecord userRecord, boolean isInsert)
        throws ProtocolException, ProtocolHttpException, InvalidProtocolBufferException,
        SigningException {

        PurekitProtosV3Crypto.EnrollmentRecord enrollmentRecord =
                PurekitProtosV3Crypto.EnrollmentRecord.parseFrom(userRecord.getPheRecord());

        byte[] userRecordSigned = PurekitProtosV3Storage.UserRecordSigned
            .newBuilder()
            .setVersion(VirgilCloudPureStorage.CURRENT_USER_SIGNED_VERSION)
            .setUserId(userRecord.getUserId())
            .setPheRecordNc(enrollmentRecord.getNc())
            .setPheRecordNs(enrollmentRecord.getNs())
            .setUpk(ByteString.copyFrom(userRecord.getUpk()))
            .setEncryptedUsk(ByteString.copyFrom(userRecord.getEncryptedUsk()))
            .setEncryptedUskBackup(ByteString.copyFrom(userRecord.getEncryptedUskBackup()))
            .setEncryptedPwdHash(ByteString.copyFrom(userRecord.getEncryptedPwdHash()))
            .build()
            .toByteArray();

        byte[] signature = crypto.generateSignature(userRecordSigned,
                                                 this.signingKey.getPrivateKey());

        PurekitProtosV3Storage.UserRecord protobufRecord = PurekitProtosV3Storage.UserRecord
            .newBuilder()
            .setVersion(VirgilCloudPureStorage.CURRENT_USER_VERSION)
            .setUserRecordSigned(ByteString.copyFrom(userRecordSigned))
            .setSignature(ByteString.copyFrom(signature))
            .setPheRecordT0(enrollmentRecord.getT0())
            .setPheRecordT1(enrollmentRecord.getT1())
            .setPheRecordVersion(userRecord.getPheRecordVersion())
            .build();

        if (isInsert) {
            client.insertUser(protobufRecord);
        } else {
            client.updateUser(userRecord.getUserId(), protobufRecord);
        }
    }

    private void insertKey(String userId, String dataId, CellKey cellKey, boolean isInsert)
        throws PureException, ProtocolException, ProtocolHttpException, SigningException {

        byte[] cellKeySigned = PurekitProtosV3Storage.CellKeySigned
            .newBuilder()
            .setVersion(VirgilCloudPureStorage.CURRENT_CELL_KEY_SIGNED_VERSION)
            .setUserId(userId)
            .setDataId(dataId)
            .setCpk(ByteString.copyFrom(cellKey.getCpk()))
            .setEncryptedCskCms(ByteString.copyFrom(cellKey.getEncryptedCskCms()))
            .setEncryptedCskBody(ByteString.copyFrom(cellKey.getEncryptedCskBody()))
            .build()
            .toByteArray();

        byte[] signature = crypto.generateSignature(cellKeySigned, this.signingKey.getPrivateKey());

        PurekitProtosV3Storage.CellKey protobufRecord = PurekitProtosV3Storage.CellKey
            .newBuilder()
            .setVersion(VirgilCloudPureStorage.CURRENT_CELL_KEY_VERSION)
            .setCellKeySigned(ByteString.copyFrom(cellKeySigned))
            .setSignature(ByteString.copyFrom(signature))
            .build();

        try {
            if (isInsert) {
                try {
                    client.insertCellKey(protobufRecord);
                } catch (ProtocolException exception) {
                    if (exception.getErrorCode()
                        == ServiceErrorCode.CELL_KEY_ALREADY_EXISTS.getCode()) {

                        throw new PureException(
                            PureException.ErrorStatus.CELL_KEY_ALREADY_EXISTS_IN_STORAGE
                        );
                    }

                    throw exception;
                }
            } else {
                client.updateCellKey(userId, dataId, protobufRecord);
            }
        } catch (ProtocolException | ProtocolHttpException exception) {
            throw exception;
        }
    }

    private UserRecord parse(PurekitProtosV3Storage.UserRecord protobufRecord)
        throws PureException, VerificationException, InvalidProtocolBufferException {

        boolean verified = crypto.verifySignature(protobufRecord.getSignature().toByteArray(),
                                              protobufRecord.getUserRecordSigned()
                                                            .toByteArray(),
                                              this.signingKey.getPublicKey());

        if (!verified) {
            throw new PureException(
                PureException.ErrorStatus.STORAGE_SIGNATURE_VERIFICATION_FAILED
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
                              protobufRecord.getPheRecordVersion(),
                              recordSigned.getUpk().toByteArray(),
                              recordSigned.getEncryptedUsk().toByteArray(),
                              recordSigned.getEncryptedUskBackup().toByteArray(),
                              recordSigned.getEncryptedPwdHash().toByteArray());
    }
}