/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(
    protected val hCoroutineContext: CoroutineContext
) : ViewModel(), CoroutineScope {

    abstract fun hHandleEvent(hEvent: T)

    protected lateinit var hJobTracker: Job

    //suggestion from Al Warren: to promote encapsulation and immutability, hide the MutableLiveData objects behind
    //LiveData references:
    protected val hErrorState = MutableLiveData<String>()
    val hError: LiveData<String> get() = hErrorState


    protected val hLoadingState = MutableLiveData<Unit>()
    val loading: LiveData<Unit> get() = hLoadingState


    init {
        hJobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = hCoroutineContext + hJobTracker

}