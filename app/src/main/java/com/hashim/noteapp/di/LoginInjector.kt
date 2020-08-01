/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.repository.LoginUserRepoImpl
import com.hashim.noteapp.ui.LoginViewModelProviderFactory

class LoginInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    fun hProvidesLoginViewModelFactory(): LoginViewModelProviderFactory {
        return LoginViewModelProviderFactory(hGetLoginRepository())
    }

    private fun hGetLoginRepository(): LoginRepository {
        return LoginUserRepoImpl()

    }

}
