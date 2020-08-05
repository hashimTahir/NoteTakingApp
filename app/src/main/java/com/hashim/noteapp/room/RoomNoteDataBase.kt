/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE = "notes"

@Database(
    entities = [RoomNote::class],
    version = 1,
    exportSchema = false
)
abstract class RoomNoteDataBase : RoomDatabase() {
    abstract fun hGetNoteDao(): NoteDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var hInstance: RoomNoteDataBase? = null
        fun hGetInstance(context: Context): RoomNoteDataBase {
            return hInstance ?: synchronized(this) {
                hInstance ?: hBuildDataBase(context).also {
                    hInstance = it
                }
            }

        }

        private fun hBuildDataBase(context: Context): RoomNoteDataBase {
            return Room.databaseBuilder(context, RoomNoteDataBase::class.java, DATABASE).build()
        }
    }
}