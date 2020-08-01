/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.models.User

interface LoginRepository {
    suspend fun hGetCurrentUser(): ResultResponse<Exception, User?>

    suspend fun hSignOutCurrentUser(): ResultResponse<Exception, Unit>

    suspend fun hSignInGoogleUser(idToken: String): ResultResponse<Exception, Unit>

}