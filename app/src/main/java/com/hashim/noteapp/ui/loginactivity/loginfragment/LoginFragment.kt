/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.loginactivity.fragment

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.hashim.noteapp.R
import com.hashim.noteapp.common.Constants
import com.hashim.noteapp.common.Constants.Companion.H_RC_SIGN_IN
import com.hashim.noteapp.common.startWithFade
import com.hashim.noteapp.events.loginevent.LoginEvent
import com.hashim.noteapp.models.LoginResult
import com.hashim.noteapp.viewmodels.LoginViewModel
import com.hashim.noteapp.ui.notesactivity.NotesActivity
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

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
        hLoginViewModel.hSignInStatusTextMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hLoginStautusDetailTv.text = it
            }
        )
        hLoginViewModel.hAuthButtonTextMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hLoginAttemptB.text = it
            })

        hLoginViewModel.hStartAnimationMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hAntennaAnimation.apply {
                    setImageResource(
                        resources.getIdentifier(
                            Constants.H_ANTENNA_LOOP, "drawable", activity?.packageName
                        )
                    )
                    var animationDrawable = drawable as AnimationDrawable
                    animationDrawable.start()
                }
            })
        hLoginViewModel.hAuthAttemptMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hStartSignInFlow()
            })

        hLoginViewModel.hSatelliteDrawableMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hAntennaAnimation.setImageResource(
                    resources.getIdentifier(it, "drawable", activity?.packageName)
                )
            })

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


    private fun hStartSignInFlow() {

        val hGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()

        val hGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), hGoogleSignInOptions)
        val hSignInIntent = hGoogleSignInClient.signInIntent
//        startActivityForResult(hSignInIntent, H_RC_SIGN_IN)

        val hStartActivityForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                var userToken: String? = null

                try {
                    val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                    if (account != null) userToken = account.idToken
                } catch (exception: Exception) {
                    Timber.d("Login Exceptoin ${exception.toString()}")
                }

                hLoginViewModel.hHandleEvent(
                    LoginEvent.OnGoogleSignInResult(
                        LoginResult(
                            H_RC_SIGN_IN,
                            userToken
                        )
                    )
                )

            }
        hStartActivityForResult.launch(hSignInIntent)
    }


    private fun hStartNotesListActivity() {
        requireActivity().startActivity(Intent(activity, NotesActivity::class.java))
    }

    private fun hSetupView() {
        val hBackground = hLoginFragmentCL.background as AnimationDrawable
        hBackground.startWithFade()
    }
}