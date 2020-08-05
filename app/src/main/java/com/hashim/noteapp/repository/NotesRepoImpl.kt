/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.hashim.noteapp.common.*
import com.hashim.noteapp.models.FirebaseNote
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

    private suspend fun hGetLocalNote(hNoteId: String): ResultResponse<Exception, Note> {
        return ResultResponse.hBuild {
            hNoteDao.getNoteById(hNoteId).hToNote
        }
    }

    private suspend fun hGetRemoteNote(
        hCreationDate: String,
        hUser: User
    ): ResultResponse<Exception, Note> {
        return try {
            val task = hAwaitTaskResult(
                hFirestore.collection(Companion.COLLECTION_NAME)
                    .document(hCreationDate + hUser.uid)
                    .get()
            )

            ResultResponse.hBuild {
                //Task<DocumentSnapshot!>
                task.toObject(FirebaseNote::class.java)?.hToNote ?: throw Exception()
            }
        } catch (exception: Exception) {
            ResultResponse.hBuild { throw exception }
        }
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

    private suspend fun hGetRemoteNotes(
        hUser: User
    ): ResultResponse<Exception, List<Note>> {
        return try {
            val task = hAwaitTaskResult(
                hFirestore.collection(Companion.COLLECTION_NAME)
                    .whereEqualTo("creator", hUser.uid)
                    .get()
            )

            hResultToNoteList(task)
        } catch (exception: Exception) {
            ResultResponse.hBuild { throw exception }
        }
    }

    private fun hResultToNoteList(task: QuerySnapshot?): ResultResponse<Exception, List<Note>> {

        val noteList = mutableListOf<Note>()

        task?.forEach { documentSnapshot ->
            noteList.add(documentSnapshot.toObject(FirebaseNote::class.java).hToNote)
        }

        return ResultResponse.hBuild {
            noteList
        }

    }

    override suspend fun hDeleteNote(hNote: Note): ResultResponse<Exception, Unit> {
        val hUser = hGetActiveUser()
        return if (hUser != null)
            hDeleteRemoteNote(hNote.copy(hCreator = hUser))
        else hDeleteLocalNote(hNote)
    }

    private suspend fun hDeleteLocalNote(hNote: Note): ResultResponse<Exception, Unit> {
        return ResultResponse.hBuild {
            hNoteDao.deleteNote(hNote.hToRoomNote)
            Unit
        }
    }

    private suspend fun hDeleteRemoteNote(hNote: Note): ResultResponse<Exception, Unit> {
        return ResultResponse.hBuild {
            hAwaitTaskCompletable(
                hFirestore.collection(Companion.COLLECTION_NAME)
                    .document(hNote.hCreationDate + hNote.hCreator!!.uid)
                    .delete()
            )
        }
    }

    override suspend fun hUpdateNote(hNote: Note): ResultResponse<Exception, Unit> {
        return ResultResponse.hBuild {
            hNoteDao.insertOrUpdateNote(hNote.hToRoomNote)
            Unit
        }
    }

    companion object {
        private const val COLLECTION_NAME = "notes"
    }

}


