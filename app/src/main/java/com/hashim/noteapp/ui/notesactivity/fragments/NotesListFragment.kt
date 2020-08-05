/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hashim.noteapp.R
import com.hashim.noteapp.common.startWithFade
import com.hashim.noteapp.di.NotesListInjector
import com.hashim.noteapp.events.NoteListEvent
import com.hashim.noteapp.viewmodels.NotesListViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*

class NotesListFragment : Fragment(R.layout.fragment_note_list) {

    private lateinit var hNoteListViewModel: NotesListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hNoteListViewModel = ViewModelProvider(
            this,
            NotesListInjector(requireActivity().application).hProvideNoteListViewModelFactory()
        ).get(NotesListViewModel::class.java)

        (imv_space_background.drawable as AnimationDrawable).startWithFade()

        hShowLoadingState()
        hSetUpAdapter()
        hSetupListeners()
        hSubscribeObservers()

        hNoteListViewModel.hHandleEvent(NoteListEvent.hOnStart)

    }

    private fun hSubscribeObservers() {
        TODO("Not yet implemented")
    }

    private fun hSetupListeners() {
        fab_create_new_item.setOnClickListener {
            val hDirections =
                NotesListFragmentDirections.actionHNoteListViewFragmentToHNoteDetailFragment()
            findNavController().navigate(hDirections)


        }
        imv_toolbar_auth.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun hShowLoadingState() {
        TODO("Not yet implemented")
    }

    private fun hSetUpAdapter() {
        TODO("Not yet implemented")
    }


}