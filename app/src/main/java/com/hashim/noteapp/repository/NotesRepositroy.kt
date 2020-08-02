/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.ResultResponse

interface NotesRepositroy {
    suspend fun hGetNoteById(noteId: String): ResultResponse<Exception, Note>

    suspend fun hGetAllNotes(): ResultResponse<Exception, List<Note>>

    suspend fun hDeleteNote(note: Note): ResultResponse<Exception, Unit>

    suspend fun hUpdateNote(note: Note): ResultResponse<Exception, Unit>
}