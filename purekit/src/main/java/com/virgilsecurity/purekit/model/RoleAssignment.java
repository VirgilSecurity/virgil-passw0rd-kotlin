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

package com.virgilsecurity.purekit.model;

import com.virgilsecurity.purekit.utils.ValidationUtils;

/**
 * Role assignment
 */
public class RoleAssignment {
    private String roleName;
    private String userId;
    private byte[] publicKeyId;
    private byte[] encryptedRsk;

    /**
     * Constructor
     *
     * @param roleName role name
     * @param userId user id
     * @param publicKeyId public key id
     * @param encryptedRsk encrypted role private key
     */
    public RoleAssignment(String roleName, String userId, byte[] publicKeyId, byte[] encryptedRsk) {
        ValidationUtils.checkNullOrEmpty(roleName, "roleName");
        ValidationUtils.checkNullOrEmpty(userId, "userId");
        ValidationUtils.checkNullOrEmpty(publicKeyId, "publicKeyId");
        ValidationUtils.checkNullOrEmpty(encryptedRsk, "encryptedRsk");

        this.roleName = roleName;
        this.userId = userId;
        this.publicKeyId = publicKeyId;
        this.encryptedRsk = encryptedRsk;
    }

    /**
     * Returns role name
     *
     * @return role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Returns public key id
     *
     * @return public key id
     */
    public byte[] getPublicKeyId() {
        return publicKeyId;
    }

    /**
     * Returns encrypted role private key
     *
     * @return encrypted role private key
     */
    public byte[] getEncryptedRsk() {
        return encryptedRsk;
    }

    /**
     * Returns user id
     *
     * @return user id
     */
    public String getUserId() {
        return userId;
    }
}
