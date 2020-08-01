/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.di

import android.app.Application
import com.hashim.noteapp.repository.LoginRepository
import com.hashim.noteapp.repository.LoginUserRepoImpl
import com.hashim.noteapp.ui.LoginViewModelProviderFactory

class LoginInjector(application: Application?) {
    fun hProvidesLoginViewModelFactory(): LoginViewModelProviderFactory {
        return LoginViewModelProviderFactory(hGetLoginRepository())
    }

    private fun hGetLoginRepository(): LoginRepository {
        return LoginUserRepoImpl()

    }

}
