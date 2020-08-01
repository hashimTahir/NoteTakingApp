/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.fragment

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hashim.noteapp.R
import com.hashim.noteapp.common.startWithFade
import com.hashim.noteapp.events.loginevent.LoginEvent
import com.hashim.noteapp.ui.loginactivity.viewmodel.LoginViewModel
import com.hashim.noteapp.ui.notesactivity.NotesActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val hLoginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hSetupView()
        hSetupListeners()
        hLoginViewModel.hHandleEvent(LoginEvent.OnStart)
        hSubscribeObservers()
    }

    private fun hSubscribeObservers() {

    }

    private fun hSetupListeners() {
        hLoginAttemptB.setOnClickListener {
            hLoginViewModel.hHandleEvent(LoginEvent.OnAuthButtonClick)
        }
        hToolBarBackIB.setOnClickListener {
            hStartNotesListActivity()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            hStartNotesListActivity()
        }

    }

    private fun hStartNotesListActivity() {
        requireActivity().startActivity(Intent(activity, NotesActivity::class.java))
    }

    private fun hSetupView() {
        val hBackground = hLoginFragmentCL.background as AnimationDrawable
        hBackground.startWithFade()
    }
}