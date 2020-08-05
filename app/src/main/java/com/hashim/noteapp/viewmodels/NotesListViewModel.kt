/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hashim.noteapp.common.Constants.Companion.H_GET_NOTES_ERROR
import com.hashim.noteapp.events.NoteListEvent
import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.repository.NotesRepositroy
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch

class NotesListViewModel(
    val hNotesRepositroy: NotesRepositroy,
    hMainCoroutineDispatcher: MainCoroutineDispatcher
) : BaseViewModel<NoteListEvent>(hMainCoroutineDispatcher) {

    private val hNotelListStateMutableLiveData = MutableLiveData<List<Note>?>()
    val hNoteListLiveData: MutableLiveData<List<Note>?> get() = hNotelListStateMutableLiveData

    private val hEditNoteStateMutableLiveData = MutableLiveData<String>()
    val hEditNoteLiveData: LiveData<String> get() = hEditNoteStateMutableLiveData

    override fun hHandleEvent(hEvent: NoteListEvent) {
        when (hEvent) {
            is NoteListEvent.hOnNoteItemClick -> hEditNote(hEvent.hPosition)
            is NoteListEvent.hOnStart -> hGetNotes()
        }
    }

    private fun hGetNotes() {
        launch {
            val hNotesList = hNotesRepositroy.hGetAllNotes()
            when (hNotesList) {
                is ResultResponse.Value -> hNotelListStateMutableLiveData.value = hNotesList.value
                is ResultResponse.Error -> hErrorState.value = H_GET_NOTES_ERROR
            }
        }
    }

    private fun hEditNote(hPosition: Int) {
        hEditNoteStateMutableLiveData.value = hNoteListLiveData.value!!.get(hPosition).hCreationDate
    }
}