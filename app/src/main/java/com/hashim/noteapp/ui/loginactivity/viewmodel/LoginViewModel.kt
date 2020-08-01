/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hashim.noteapp.events.loginevent.LoginEvent
import com.hashim.noteapp.models.LoginResult
import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.models.User
import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.ui.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    val hLoginRepository: LoginRepository,
    hCoroutineContext: CoroutineContext
) : BaseViewModel<LoginEvent<LoginResult>>(hCoroutineContext) {


    private val hUserState = MutableLiveData<User?>()


    init {
        Timber.d("View Model injected")
    }


    override fun hHandleEvent(hEvent: LoginEvent<LoginResult>) {
        hShowLoadingState()
        when (hEvent) {
            is LoginEvent.OnAuthButtonClick -> hOnAuthButtonClick()
            is LoginEvent.OnStart -> hGetUser()
            is LoginEvent.OnGoogleSignInResult -> hGoogleSignInResult(hEvent.result)
        }
    }

    private fun hGetUser() {
        launch {
            var hResultResponse = hLoginRepository.hGetCurrentUser()
            when (hResultResponse) {
                is ResultResponse.Value -> {
                    hUserState.value = hResultResponse.value
                    if (hResultResponse.value == null)
                        hShowSignedOutState()
                    else
                        hShowSignedInState()
                }
                is ResultResponse.Error -> hShowErrorState()
            }
        }
    }

    private fun hShowSignedInState() {
        TODO("Not yet implemented")
    }

    private fun hShowSignedOutState() {
        TODO("Not yet implemented")
    }

    private fun hShowErrorState() {
        TODO("Not yet implemented")
    }

    private fun hOnAuthButtonClick() {
        TODO("Not yet implemented")
    }

    private fun hGoogleSignInResult(result: LoginResult) {
        TODO("Not yet implemented")
    }

    private fun hShowLoadingState() {
        TODO("Not yet implemented")
    }


}