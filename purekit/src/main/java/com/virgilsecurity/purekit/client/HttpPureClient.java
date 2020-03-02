/*
 * Copyright (c) 2015-2020, Virgil Security, Inc.
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

package com.virgilsecurity.purekit.client;

import com.virgilsecurity.common.exception.EmptyArgumentException;
import com.virgilsecurity.purekit.data.ProtocolException;
import com.virgilsecurity.purekit.data.ProtocolHttpException;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Client;
import com.virgilsecurity.purekit.protobuf.build.PurekitProtosV3Storage;
import com.virgilsecurity.purekit.utils.ValidationUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for http interactions with Pure service
 */
public class HttpPureClient {

    private final String appToken;
    private final HttpClientProtobuf client;

    /**
     * Pure service url
     */
    public static final String SERVICE_ADDRESS = "https://api.virgilsecurity.com/pure/v1";

    private static final String KEY_CASCADE = "cascade";

    /**
     * Instantiates HttpPureClient.
     *
     * @param appToken Application token.
     * @param serviceAddress Service url.
     */
    public HttpPureClient(String appToken, String serviceAddress) {
        ValidationUtils.checkNullOrEmpty(appToken, "appToken");
        ValidationUtils.checkNullOrEmpty(serviceAddress, "serviceAddress");

        this.appToken = appToken;
        this.client = new HttpClientProtobuf(serviceAddress);
    }

    /**
     * Inserts new user.
     *
     * @param userRecord User Record.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void insertUser(PurekitProtosV3Storage.UserRecord userRecord) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(userRecord, "userRecord");
        
        client.firePost(
                userRecord,
                "/user",
                this.appToken
        );
    }

    /**
     * Updates user.
     *
     * @param userId User Id.
     * @param userRecord UserRecord.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void updateUser(String userId, PurekitProtosV3Storage.UserRecord userRecord) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(userRecord, "userRecord");
        ValidationUtils.checkNullOrEmpty(userId, "userId");

        client.firePut(
                userRecord,
                String.format("/user/%s", userId),
                this.appToken
        );
    }

    /**
     * Obtains user.
     *
     * @param userId User Id.
     *
     * @return UserRecord.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.UserRecord getUser(String userId) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNullOrEmpty(userId, "userId");

        return client.fireGet(
                String.format("/user/%s", userId),
                this.appToken,
                PurekitProtosV3Storage.UserRecord.parser()
        );
    }

    /**
     * Obtains user.
     *
     * @param userIds User Ids.
     *
     * @return UserRecords.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.UserRecords getUsers(Collection<String> userIds) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(userIds, "userIds");
        if (userIds.isEmpty()) {
            throw new EmptyArgumentException("userIds");
        }

        PurekitProtosV3Client.GetUserRecords getUserRecords =
            PurekitProtosV3Client.GetUserRecords.newBuilder().addAllUserIds(userIds).build();

        return client.firePost(
                getUserRecords,
                "/get-users",
                this.appToken,
                PurekitProtosV3Storage.UserRecords.parser()
        );
    }

    /**
     * Deletes user.
     *
     * @param userId User Ids.
     * @param cascade Deletes all user cell keys if true.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void deleteUser(String userId, boolean cascade) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNullOrEmpty(userId, "userId");

        Map<String, String> params = new HashMap<>();
        params.put(KEY_CASCADE, String.valueOf(cascade));

        client.fireDelete(
                params,
                String.format("/user/%s", userId),
                this.appToken
        );
    }

    /**
     * Inserts new cell key.
     *
     * @param cellKey CellKey.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void insertCellKey(PurekitProtosV3Storage.CellKey cellKey) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(cellKey, "cellKey");
        
        client.firePost(
                cellKey,
                "/cell-key",
                this.appToken
        );
    }

    /**
     * Updates cell key.
     *
     * @param userId User Id.
     * @param dataId Data id.
     * @param cellKey CellKey.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void updateCellKey(String userId, String dataId, PurekitProtosV3Storage.CellKey cellKey) 
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNullOrEmpty(userId, "userId");
        ValidationUtils.checkNullOrEmpty(dataId, "dataId");
        ValidationUtils.checkNull(cellKey, "cellKey");

        client.firePut(
                cellKey,
                String.format("/cell-key/%s/%s", userId, dataId),
                this.appToken
        );
    }

    /**
     * Obtains cell key.
     *
     * @param userId User Id.
     * @param dataId Data Id.
     *
     * @return CellKey.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.CellKey getCellKey(String userId, String dataId)
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNullOrEmpty(userId, "userId");
        ValidationUtils.checkNullOrEmpty(dataId, "dataId");

        return client.fireGet(
                String.format("/cell-key/%s/%s", userId, dataId),
                this.appToken,
                PurekitProtosV3Storage.CellKey.parser()
        );
    }

    /**
     * Deletes cell key.
     *
     * @param userId User Ids.
     * @param dataId Data Id.
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void deleteCellKey(String userId, String dataId)
        throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNullOrEmpty(userId, "userId");
        ValidationUtils.checkNullOrEmpty(dataId, "dataId");

        client.fireDelete(
                String.format("/cell-key/%s/%s", userId, dataId),
                this.appToken
        );
    }

    /**
     *
     * Inserts role
     *
     * @param role Role
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void insertRole(PurekitProtosV3Storage.Role role)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(role, "role");

        client.firePost(
                role,
                "/roles",
                this.appToken
        );
    }

    /**
     * Obtains roles
     *
     * @param getRolesRequest Role names
     *
     * @return Roles
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.Roles getRoles(PurekitProtosV3Client.GetRoles getRolesRequest)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(getRolesRequest, "getRolesRequest");

        return client.firePost(
                getRolesRequest,
                "/get-roles",
                this.appToken,
                PurekitProtosV3Storage.Roles.parser()
        );
    }

    /**
     * Inserts roles assignments
     *
     * @param roleAssignments role assignments
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void insertRoleAssignments(PurekitProtosV3Storage.RoleAssignments roleAssignments)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(roleAssignments, "roleAssignments");
        if (roleAssignments.getRoleAssignmentsList().isEmpty()) {
            throw new EmptyArgumentException("roleAssignments");
        }

        client.firePost(
                roleAssignments,
                "/role-assignments",
                this.appToken
        );
    }

    /**
     * Obtains role assignments
     *
     * @param request request
     *
     * @return Role assignments
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.RoleAssignments getRoleAssignments(PurekitProtosV3Client.GetRoleAssignments request)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(request, "request");

        return client.firePost(
                request,
                "/get-role-assignments",
                this.appToken,
                PurekitProtosV3Storage.RoleAssignments.parser()
        );
    }

    /**
     * Obtains role assignmane
     *
     * @param request request
     *
     * @return RoleAssignment
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.RoleAssignment getRoleAssignment(PurekitProtosV3Client.GetRoleAssignment request)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(request, "request");

        return client.firePost(
                request,
                "/get-role-assignment",
                this.appToken,
                PurekitProtosV3Storage.RoleAssignment.parser()
        );
    }

    /**
     * Delete role assignments
     *
     * @param request request
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void deleteRoleAssignments(PurekitProtosV3Client.DeleteRoleAssignments request)
            throws ProtocolHttpException, ProtocolException {

        ValidationUtils.checkNull(request, "request");

        client.firePost(
                request,
                "/delete-role-assignments",
                this.appToken
        );
    }

    /**
     * Inserts new grant key
     *
     * @param grantKey grant key
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void insertGrantKey(PurekitProtosV3Storage.GrantKey grantKey) throws ProtocolHttpException, ProtocolException {
        ValidationUtils.checkNull(grantKey, "grantKey");

        client.firePost(
                grantKey,
                "/grant-key",
                this.appToken
        );
    }

    /**
     * Obtains grant key
     *
     * @param request request
     *
     * @return grant key
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public PurekitProtosV3Storage.GrantKey getGrantKey(PurekitProtosV3Client.GrantKeyDescriptor request) throws ProtocolHttpException, ProtocolException {
        ValidationUtils.checkNull(request, "request");

        return client.firePost(
                request,
                "/get-grant-key",
                this.appToken,
                PurekitProtosV3Storage.GrantKey.parser()
        );
    }

    /**
     * Deletes grant key
     *
     * @param request request
     *
     * @throws ProtocolException Thrown if an error from the PHE service has been parsed
     * successfully.
     * @throws ProtocolHttpException Thrown if an error from the PHE service has NOT been parsed
     * successfully. Represents a regular HTTP exception with code and message.
     */
    public void deleteGrantKey(PurekitProtosV3Client.GrantKeyDescriptor request) throws ProtocolHttpException, ProtocolException {
        ValidationUtils.checkNull(request, "request");

        client.firePost(
                request,
                "/delete-grant-key",
                this.appToken
        );
    }
}
