/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hashim.noteapp.common.Constants.Companion.H_ANTENNA_EMPTY
import com.hashim.noteapp.common.Constants.Companion.H_ANTENNA_FULL
import com.hashim.noteapp.common.Constants.Companion.H_ANTENNA_LOOP
import com.hashim.noteapp.common.Constants.Companion.H_LOADING
import com.hashim.noteapp.common.Constants.Companion.H_LOGIN_ERROR
import com.hashim.noteapp.common.Constants.Companion.H_RC_SIGN_IN
import com.hashim.noteapp.common.Constants.Companion.H_SIGNED_IN
import com.hashim.noteapp.common.Constants.Companion.H_SIGNED_OUT
import com.hashim.noteapp.common.Constants.Companion.H_SIGN_IN
import com.hashim.noteapp.common.Constants.Companion.H_SIGN_OUT
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

    //The actual data model is kept private to avoid unwanted tampering
    private val hUserStateMutableLiveData = MutableLiveData<User?>()

    //UI Binding
    internal val hSignInStatusTextMutableLiveData = MutableLiveData<String>()
    internal val hAuthButtonTextMutableLiveData = MutableLiveData<String>()
    internal val hSatelliteDrawableMutableLiveData = MutableLiveData<String>()

    //Control Logic
    internal val hAuthAttemptMutableLiveData = MutableLiveData<Unit>()
    internal val hStartAnimationMutableLiveData = MutableLiveData<Unit>()


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
                    hUserStateMutableLiveData.value = hResultResponse.value
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
        hSignInStatusTextMutableLiveData.value = H_SIGNED_IN
        hAuthButtonTextMutableLiveData.value = H_SIGN_OUT
        hSatelliteDrawableMutableLiveData.value = H_ANTENNA_FULL
    }

    private fun hShowSignedOutState() {
        hSignInStatusTextMutableLiveData.value = H_SIGNED_OUT
        hAuthButtonTextMutableLiveData.value = H_SIGN_IN
        hSatelliteDrawableMutableLiveData.value = H_ANTENNA_EMPTY
    }

    private fun hShowErrorState() {
        hSignInStatusTextMutableLiveData.value = H_LOGIN_ERROR
        hAuthButtonTextMutableLiveData.value = H_SIGN_IN
        hSatelliteDrawableMutableLiveData.value = H_ANTENNA_EMPTY

    }

    private fun hOnAuthButtonClick() {
        if (hUserStateMutableLiveData.value == null)
            hAuthAttemptMutableLiveData.value = Unit
        else
            hSignOutUser()
    }

    private fun hSignOutUser() {
        launch {
            var hSignOutCurrentUser = hLoginRepository.hSignOutCurrentUser()
            when (hSignOutCurrentUser) {
                is ResultResponse.Value -> {
                    hUserStateMutableLiveData.value = null
                    hShowSignedOutState()
                }
                is ResultResponse.Error -> hShowErrorState()
            }
        }
    }

    private fun hGoogleSignInResult(hResult: LoginResult) {
        launch {
            if (hResult.hRequestCode == H_RC_SIGN_IN && hResult.hUserToken != null) {
                val hCreateGoogleUser = hLoginRepository.hSignInGoogleUser(hResult.hUserToken)
                if (hCreateGoogleUser is ResultResponse.Value) {
                    hGetUser()
                } else {
                    hShowErrorState()
                }
            } else {
                hShowErrorState()
            }

        }
    }

    private fun hShowLoadingState() {
        hSignInStatusTextMutableLiveData.value = H_LOADING
        hSatelliteDrawableMutableLiveData.value = H_ANTENNA_LOOP
        hStartAnimationMutableLiveData.value = Unit

    }


}