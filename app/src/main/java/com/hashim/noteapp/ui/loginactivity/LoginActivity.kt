/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hashim.noteapp.R
import com.hashim.noteapp.di.LoginInjector
import com.hashim.noteapp.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private var hLoginNavHostFragment: NavHostFragment? = null
    private lateinit var hNavController: NavController
    private lateinit var hLoginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hLoginViewModel =
            ViewModelProvider(
                this,
                LoginInjector(application).hProvidesLoginViewModelFactory()
            ).get(LoginViewModel::class.java)

        hInitNavController()
    }


    private fun hInitNavController() {
        hLoginNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.hLoginNavHostFragment) as NavHostFragment?
        hNavController = hLoginNavHostFragment!!.navController
        hNavController.setGraph(R.navigation.login_activity_nav_graph)
    }
}