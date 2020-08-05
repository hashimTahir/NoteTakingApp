/*
 * Copyright (c) 2020/  8/ 5.  Created by Hashim Tahir
 */

package com.hashim.noteapp.models

data class FirebaseNote(
    val creationDate: String? = "",
    val contents: String? = "",
    val upVotes: Int? = 0,
    val imageurl: String? = "",
    val creator: String? = ""
)
