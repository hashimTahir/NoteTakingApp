/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hashim.noteapp.common.hAwaitTaskCompletable
import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUserRepoImpl(
    val hFireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : LoginRepository {
    override suspend fun hGetCurrentUser(): ResultResponse<Exception, User?> {
        val hFirebaseUser = hFireBaseAuth.currentUser

        return if (hFirebaseUser == null) {
            ResultResponse.hBuild { null }
        } else {
            ResultResponse.hBuild {
                User(
                    hFirebaseUser.uid,
                    hFirebaseUser.displayName ?: ""
                )
            }
        }
    }

    override suspend fun hSignOutCurrentUser(): ResultResponse<Exception, Unit> {
        return ResultResponse.hBuild {
            hFireBaseAuth.signOut()
        }

    }

    override suspend fun hSignInGoogleUser(idToken: String): ResultResponse<Exception, Unit> {
        return withContext(Dispatchers.Main) {
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                hAwaitTaskCompletable(hFireBaseAuth.signInWithCredential(credential))

                ResultResponse.hBuild { Unit }
            } catch (exception: Exception) {
                ResultResponse.hBuild { throw exception }
            }
        }
    }

}
