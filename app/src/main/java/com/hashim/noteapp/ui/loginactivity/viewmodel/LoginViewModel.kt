/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.viewmodel

import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.ui.BaseViewModel
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    val hLoginRepository: LoginRepository,
    hCoroutineContext: CoroutineContext
) : BaseViewModel<LoginEvent<LoginResult>>(hCoroutineContext) {

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")


}