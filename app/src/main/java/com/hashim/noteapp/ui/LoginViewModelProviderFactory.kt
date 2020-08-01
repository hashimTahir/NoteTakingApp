/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.ui.loginactivity.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers

class LoginViewModelProviderFactory(
    private val hLoginRepository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            hLoginRepository,
            Dispatchers.Main
        ) as T
    }
}