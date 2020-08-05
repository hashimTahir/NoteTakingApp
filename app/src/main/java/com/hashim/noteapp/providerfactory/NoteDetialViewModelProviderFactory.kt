/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.providerfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hashim.noteapp.repository.NotesRepositroy
import com.hashim.noteapp.viewmodels.NoteDetailViewModel
import kotlinx.coroutines.Dispatchers

class NoteDetialViewModelProviderFactory(
    private val hNotesRepo: NotesRepositroy
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteDetailViewModel(hNotesRepo, Dispatchers.Main) as T
    }
}
