/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.viewmodel

import com.hashim.noteapp.events.loginevent.LoginEvent
import com.hashim.noteapp.models.LoginResult
import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.ui.BaseViewModel
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    val hLoginRepository: LoginRepository,
    hCoroutineContext: CoroutineContext
) : BaseViewModel<LoginEvent<LoginResult>>(hCoroutineContext) {

    init {
        Timber.d("View Model injected")
    }

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")


}