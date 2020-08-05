/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.events

sealed class NoteListEvent {
    data class hOnNoteItemClick(val hPosition: Int) : NoteListEvent()
    object hOnNewNoteClick : NoteListEvent()
    object hOnStart : NoteListEvent()
}
