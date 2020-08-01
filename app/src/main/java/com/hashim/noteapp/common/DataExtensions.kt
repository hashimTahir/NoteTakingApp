/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.common

import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//Wraps Firebase/GMS calls
internal suspend fun <T> hAwaitTaskCompletable(task: Task<T>): Unit = suspendCoroutine { continuation ->
    task.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(Unit)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}