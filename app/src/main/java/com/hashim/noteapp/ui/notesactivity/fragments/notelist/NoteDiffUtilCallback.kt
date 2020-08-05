package com.hashim.noteapp.ui.notesactivity.fragments.notelist


import androidx.recyclerview.widget.DiffUtil
import com.hashim.noteapp.models.Note

class NoteDiffUtilCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.hCreationDate == newItem.hCreationDate
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.hCreationDate == newItem.hCreationDate
    }
}