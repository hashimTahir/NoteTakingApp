/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.common

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.hashim.noteapp.models.Note
import com.hashim.noteapp.models.User
import com.hashim.noteapp.room.RoomNote
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private val RoomNote.toNote: Note
    get() {
        return Note(
            this.creationDate,
            this.contents,
            this.upVotes,
            this.imageUrl,
            User(this.creatorId)
        )

    }

//Wraps Firebase/GMS calls
internal suspend fun <T> hAwaitTaskCompletable(task: Task<T>): Unit =
    suspendCoroutine { continuation ->
        task.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(Unit)
            } else {
                continuation.resumeWithException(task.exception!!)
            }
        }


    }

internal fun List<RoomNote>.htoNoteListFromRoomNote(): List<Note> {
    return this.flatMap {
        listOf(it.toNote)
    }

}

internal val FirebaseUser.toUser: User
    get() = User(
        uid = this.uid,
        name = this.displayName ?: ""
    )
