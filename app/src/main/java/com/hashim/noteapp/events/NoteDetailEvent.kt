/*
 * Copyright (c) 2020/  8/ 6.  Created by Hashim Tahir
 */

package com.hashim.noteapp.events


sealed class NoteDetailEvent {
    data class hOnDoneClick(val hContents: String) : NoteDetailEvent()
    object hOnDeleteClick : NoteDetailEvent()
    object hOnDeleteConfirmed : NoteDetailEvent()
    data class hOnStart(val hNoteId: String) : NoteDetailEvent()
}



