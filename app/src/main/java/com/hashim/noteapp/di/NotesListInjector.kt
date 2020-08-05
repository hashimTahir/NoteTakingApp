/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import com.hashim.noteapp.repository.NotesRepoImpl
import com.hashim.noteapp.repository.NotesRepositroy
import com.hashim.noteapp.room.RoomNoteDataBase

class NotesListInjector(hApplication: Application) : AndroidViewModel(hApplication) {
    private fun hGetNoteRepository(): NotesRepositroy {
        FirebaseApp.initializeApp(getApplication())
        return NotesRepoImpl(
            hNoteDao = RoomNoteDataBase.getInstance(getApplication()).roomNoteDao()
        )
    }

    fun provideNoteListViewModelFactory(): NoteListViewModelFactory =
        NoteListViewModelFactory(
            hGetNoteRepository()
        )


}