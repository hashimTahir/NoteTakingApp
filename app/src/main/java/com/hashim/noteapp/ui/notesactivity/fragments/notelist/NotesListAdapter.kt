/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity.fragments.notelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hashim.noteapp.R
import com.hashim.noteapp.events.NoteListEvent
import com.hashim.noteapp.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesListAdapter(
    val hNoteListEventMutableLiveData: MutableLiveData<NoteListEvent> = MutableLiveData()
) : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(NoteDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
            inflater.inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let { note ->
            holder.content.text = note.hContents
            holder.date.text = note.hCreationDate

            holder.itemView.setOnClickListener {
                hNoteListEventMutableLiveData.value = NoteListEvent.hOnNoteItemClick(position)
            }
        }
    }

    class NoteViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
        var date: TextView = root.lbl_date_and_time
    }

}