/*
 * Copyright (c) 2020/  8/ 6.  Created by Hashim Tahir
 */

package com.hashim.noteapp.events

sealed class LoginEvent<out T> {
    object OnAuthButtonClick : LoginEvent<Nothing>()
    object OnStart : LoginEvent<Nothing>()
    data class OnGoogleSignInResult<out LoginResult>(val result: LoginResult) :
        LoginEvent<LoginResult>()
}