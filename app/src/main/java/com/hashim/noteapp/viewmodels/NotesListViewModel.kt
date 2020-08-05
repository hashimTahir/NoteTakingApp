/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.viewmodels

import com.hashim.noteapp.events.NoteListEvent
import com.hashim.noteapp.repository.NotesRepositroy
import kotlinx.coroutines.MainCoroutineDispatcher

class NotesListViewModel(
    val hNotesRepositroy: NotesRepositroy,
    hMainCoroutineDispatcher: MainCoroutineDispatcher
) : BaseViewModel<NoteListEvent>(hMainCoroutineDispatcher) {

    override fun hHandleEvent(hEvent: NoteListEvent) {
        TODO("Not yet implemented")
    }
}