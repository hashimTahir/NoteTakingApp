/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity.fragments.notelist

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hashim.noteapp.R
import com.hashim.noteapp.common.makeToast
import com.hashim.noteapp.common.startWithFade
import com.hashim.noteapp.di.NotesListInjector
import com.hashim.noteapp.events.NoteListEvent
import com.hashim.noteapp.ui.notesactivity.fragments.NotesListFragmentDirections
import com.hashim.noteapp.viewmodels.NotesListViewModel
import kotlinx.android.synthetic.main.fragment_note_list.*

class NotesListFragment : Fragment(R.layout.fragment_note_list) {

    private lateinit var hNoteListViewModel: NotesListViewModel
    private lateinit var hNotesListAdapter: NotesListAdapter


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
        hNoteListViewModel.hError.observe(viewLifecycleOwner, Observer {
            hShowErrorState(it)
        })
        hNoteListViewModel.hNoteListLiveData.observe(viewLifecycleOwner, Observer {
            hNotesListAdapter.submitList(it)

            if (it?.isNotEmpty()!!) {
                (imv_satellite_animation.drawable as AnimationDrawable).stop()
                imv_satellite_animation.visibility = View.INVISIBLE
                rec_list_fragment.visibility = View.VISIBLE
            }

        })
        hNoteListViewModel.hEditNoteLiveData.observe(viewLifecycleOwner, Observer {
            hStartNoteDetailWithArgs(it)
        })
    }

    private fun hStartNoteDetailWithArgs(it: String?) {
        if (it != null) {
            NotesListFragmentDirections.actionHNoteListViewFragmentToHNoteDetailFragment(it)
        }

    }

    private fun hShowErrorState(it: String) {
        makeToast(it)

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
        return (imv_satellite_animation.drawable as AnimationDrawable).start()
    }

    private fun hSetUpAdapter() {
        hNotesListAdapter = NotesListAdapter()
        hNotesListAdapter.hNoteListEventMutableLiveData.observe(viewLifecycleOwner,
            Observer {
                hNoteListViewModel.hHandleEvent(it)
            }
        )
        rec_list_fragment.adapter = hNotesListAdapter

    }


}