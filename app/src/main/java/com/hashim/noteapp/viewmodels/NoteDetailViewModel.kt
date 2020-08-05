/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hashim.noteapp.common.Constants.Companion.H_GET_NOTE_ERROR
import com.hashim.noteapp.events.NoteDetailEvent
import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.repository.NotesRepositroy
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailViewModel(
    val hNotesRepo: NotesRepositroy,
    hMainCoroutineDispatcher: MainCoroutineDispatcher
) : BaseViewModel<NoteDetailEvent>(hMainCoroutineDispatcher) {

    private val hNoteStateMutableLiveData = MutableLiveData<Note>()
    val hNoteLiveData: LiveData<Note> get() = hNoteStateMutableLiveData

    private val hDeletedStateMutableLiveData = MutableLiveData<Boolean>()
    val hDeletedLiveData: LiveData<Boolean> get() = hDeletedStateMutableLiveData

    private val hUpdatedStateMutableLiveData = MutableLiveData<Boolean>()
    val hUpdatedLiveData: LiveData<Boolean> get() = hUpdatedStateMutableLiveData


    override fun hHandleEvent(hEvent: NoteDetailEvent) {
        when (hEvent) {
            is NoteDetailEvent.hOnStart -> hGetNote(hEvent.hNoteId)
            is NoteDetailEvent.hOnDeleteClick -> hDeleteNote()
            is NoteDetailEvent.hOnDoneClick -> hUpdateNote(hEvent.hContents)
        }
    }

    private fun hUpdateNote(contents: String) {
        launch {
            val hUpdateNote =
                hNotesRepo.hUpdateNote(hNoteLiveData.value!!.copy(hContents = contents))
            when (hUpdateNote) {
                is ResultResponse.Error -> hUpdatedStateMutableLiveData.value = false
                is ResultResponse.Value -> hUpdatedStateMutableLiveData.value = true
            }
        }
    }

    private fun hDeleteNote() {
        launch {
            val hDeleteNote = hNotesRepo.hDeleteNote(hNoteLiveData.value!!)
            when (hDeleteNote) {
                is ResultResponse.Value -> hDeletedStateMutableLiveData.value = true
                is ResultResponse.Error -> hDeletedStateMutableLiveData.value = false
            }
        }
    }

    private fun hGetNote(hNoteId: String) {
        launch {
            if (hNoteId.equals("")) {
                hCreateNewNote()
            } else {
                val hGetNoteById = hNotesRepo.hGetNoteById(hNoteId)
                when (hGetNoteById) {
                    is ResultResponse.Error -> hErrorState.value = H_GET_NOTE_ERROR
                    is ResultResponse.Value -> hNoteStateMutableLiveData.value =
                        hGetNoteById.value
                }
            }

        }
    }

    private fun hCreateNewNote() {
        hNoteStateMutableLiveData.value = Note(hGetCalendarTime(), "", 0, "rocket_loop", null)
    }

    private fun hGetCalendarTime(): String {
        var hCalender = Calendar.getInstance(TimeZone.getDefault())
        val hFormat = SimpleDateFormat("d MMM yyyy HH:mm:ss Z")
        hFormat.timeZone = hCalender.timeZone
        return hFormat.format(hCalender.time)
    }
}