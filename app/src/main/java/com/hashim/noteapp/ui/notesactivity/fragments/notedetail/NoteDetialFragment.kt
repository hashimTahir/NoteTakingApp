/*
 * Copyright (c) 2020/  8/ 6.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity.fragments.notedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hashim.noteapp.R
import com.hashim.noteapp.di.NoteDetailInjector
import com.hashim.noteapp.viewmodels.NoteDetailViewModel


class NoteDetialFragment : Fragment(R.layout.fragment_note_detail) {
    lateinit var hNoteDetailViewModel: NoteDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hNoteDetailViewModel = ViewModelProvider(
            this,
            NoteDetailInjector(requireActivity().application).hProvidesNoteDetailViewModelFactory()
        ).get(NoteDetailViewModel::class.java)
        hSubscribeObservers()
    }

    private fun hSubscribeObservers() {
        TODO("Not yet implemented")
    }


}