/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.providerfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hashim.noteapp.repository.NotesRepositroy
import com.hashim.noteapp.viewmodels.NotesListViewModel
import kotlinx.coroutines.Dispatchers

@Suppress("UNCHECKED_CAST")
class NotesListViewModelProviderFactory(
    private val hNotesRepositroy: NotesRepositroy
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(hNotesRepositroy, Dispatchers.Main) as T
    }

}