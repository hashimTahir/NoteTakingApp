/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.ResultResponse

class NotesRepoImpl : NotesRepositroy {
    override suspend fun hGetNoteById(hNoteId: String): ResultResponse<Exception, Note> {
        TODO("Not yet implemented")
    }

    override suspend fun hGetAllNotes(): ResultResponse<Exception, List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun hDeleteNote(hNote: Note): ResultResponse<Exception, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun hUpdateNote(hNote: Note): ResultResponse<Exception, Unit> {
        TODO("Not yet implemented")
    }
}