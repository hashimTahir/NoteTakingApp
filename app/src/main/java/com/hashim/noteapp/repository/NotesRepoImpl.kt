/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hashim.noteapp.common.htoNoteListFromRoomNote
import com.hashim.noteapp.common.toUser
import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.ResultResponse
import com.hashim.noteapp.models.User
import com.hashim.noteapp.room.NoteDao

class NotesRepoImpl(
    val hFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    val hFirestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    val hNoteDao: NoteDao
) : NotesRepositroy {
    override suspend fun hGetNoteById(hNoteId: String): ResultResponse<Exception, Note> {
        val hUser = hGetActiveUser()
        return if (hUser != null)
            hGetRemoteNote(hNoteId, hUser)
        else
            hGetLocalNote(hNoteId)
    }

    private fun hGetLocalNote(hNoteId: String): ResultResponse<Exception, Note> {
        TODO("Not yet implemented")
    }

    private fun hGetRemoteNote(hNoteId: String, user: Any): ResultResponse<Exception, Note> {
        TODO("Not yet implemented")
    }

    private fun hGetActiveUser(): User? {
        return hFirebaseAuth.currentUser?.toUser
    }

    override suspend fun hGetAllNotes(): ResultResponse<Exception, List<Note>> {
        val hUser = hGetActiveUser()
        return if (hUser != null)
            hGetRemoteNotes(hUser)
        else
            hGetLocalNotes()
    }

    private suspend fun hGetLocalNotes(): ResultResponse<Exception, List<Note>> {
        return ResultResponse.hBuild {
            hNoteDao.getNotes().htoNoteListFromRoomNote()
        }
    }

    private fun hGetRemoteNotes(hUser: Any): ResultResponse<Exception, List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun hDeleteNote(hNote: Note): ResultResponse<Exception, Unit> {
        val hUser = hGetActiveUser()
        return if (hUser != null)
            hDeleteRemoteNote(hNote.copy(hCreator = hUser))
        else hDeleteLocalNote(hNote)
    }

    private fun hDeleteLocalNote(hNote: Any): ResultResponse<Exception, Unit> {
        TODO("Not yet implemented")
    }

    private fun hDeleteRemoteNote(copy: Note): ResultResponse<Exception, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun hUpdateNote(hNote: Note): ResultResponse<Exception, Unit> {
        TODO("Not yet implemented")
    }
}


