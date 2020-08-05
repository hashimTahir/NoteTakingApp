/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import com.hashim.noteapp.providerfactory.NoteDetialViewModelProviderFactory
import com.hashim.noteapp.repository.NotesRepoImpl
import com.hashim.noteapp.repository.NotesRepositroy
import com.hashim.noteapp.room.RoomNoteDataBase

class NoteDetailInjector(application: Application) : AndroidViewModel(application) {

    fun hProvidesNoteDetailViewModelFactory(): NoteDetialViewModelProviderFactory {
        return NoteDetialViewModelProviderFactory(hGetNoteRepository())
    }

    private fun hGetNoteRepository(): NotesRepositroy {
        FirebaseApp.initializeApp(getApplication())
        return NotesRepoImpl(
            hNoteDao = RoomNoteDataBase.hGetInstance(getApplication()).hGetNoteDao()
        )

    }

}