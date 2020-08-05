/*
 * Copyright (c) 2020/  8/ 6.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity.fragments.notedetail

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hashim.noteapp.R
import com.hashim.noteapp.common.makeToast
import com.hashim.noteapp.common.startWithFade
import com.hashim.noteapp.common.toEditable
import com.hashim.noteapp.di.NoteDetailInjector
import com.hashim.noteapp.events.NoteDetailEvent
import com.hashim.noteapp.viewmodels.NoteDetailViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.*


class NoteDetialFragment : Fragment(R.layout.fragment_note_detail) {
    lateinit var hNoteDetailViewModel: NoteDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hNoteDetailViewModel = ViewModelProvider(
            this,
            NoteDetailInjector(requireActivity().application).hProvidesNoteDetailViewModelFactory()
        ).get(NoteDetailViewModel::class.java)

        hShowLoadingState()
        hSetupListeners()


        hSubscribeObservers()

        (frag_note_detail.background as AnimationDrawable).startWithFade()

        hNoteDetailViewModel.hHandleEvent(
            NoteDetailEvent.hOnStart(
                NoteDetialFragmentArgs.fromBundle(requireArguments()).noteId
            )
        )
    }

    private fun hSetupListeners() {

        imb_toolbar_done.setOnClickListener {
            hNoteDetailViewModel.hHandleEvent(
                NoteDetailEvent.hOnDoneClick(
                    edt_note_detail_text.text.toString()
                )
            )
        }

        imb_toolbar_delete.setOnClickListener {
            hNoteDetailViewModel.hHandleEvent(
                NoteDetailEvent.hOnDeleteClick
            )
        }
    }

    private fun hShowLoadingState() {
        (imv_note_detail_satellite.drawable as AnimationDrawable).start()
    }

    private fun hSubscribeObservers() {
        hNoteDetailViewModel.hDeletedLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_hNoteDetailFragment_to_hNoteListViewFragment)

        })
        hNoteDetailViewModel.hNoteLiveData.observe(viewLifecycleOwner, Observer { hNote ->
            edt_note_detail_text.text = hNote.hContents.toEditable()

        })
        hNoteDetailViewModel.hUpdatedLiveData.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_hNoteDetailFragment_to_hNoteListViewFragment)

        })
        hNoteDetailViewModel.hError.observe(viewLifecycleOwner, Observer { hError ->
            hShowErrorState(hError)

        })

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_hNoteDetailFragment_to_hNoteListViewFragment)
        }
    }

    private fun hShowErrorState(hError: String?) {
        makeToast(hError!!)
        findNavController().navigate(R.id.action_hNoteDetailFragment_to_hNoteListViewFragment)
    }

}