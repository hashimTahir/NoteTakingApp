/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hashim.noteapp.R

class LoginActivity : AppCompatActivity() {
    private var hNewNavHostFragment: NavHostFragment? = null
    private lateinit var hNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hInitNavController()

    }


    private fun hInitNavController() {
        hNewNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.hLoginNavHostFragment) as NavHostFragment?
        hNavController = hNewNavHostFragment!!.navController
        hNavController.setGraph(R.navigation.login_activity_nav_graph)
    }
}