/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.room

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun hGetAllNotes(): List<RoomNote>

    @Query("SELECT * FROM notes WHERE creation_date = :creationDate")
    suspend fun hGetNoteById(creationDate: String): RoomNote

    @Delete
    suspend fun hDeleteNote(note: RoomNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun hInsertOrUpdateNote(note: RoomNote): Long
}