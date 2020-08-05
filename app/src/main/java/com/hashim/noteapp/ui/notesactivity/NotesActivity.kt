/*
 * Copyright (c) 2020/  8/ 1.  Created by Hashim Tahir
 */

package com.hashim.noteapp.ui.notesactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.hashim.noteapp.R
import com.hashim.noteapp.ui.notesactivity.viewmodel.NotesViewModel

class NotesActivity : AppCompatActivity() {
    private var hNotesNavHostFragment: NavHostFragment? = null
    private lateinit var hNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        hInitNavController()

    }

    private fun hInitNavController() {
        hNotesNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.hNotesNavHostFragment) as NavHostFragment?
        hNavController = hNotesNavHostFragment!!.navController
        hNavController.setGraph(R.navigation.notes_activity_nav_graph)
    }
}