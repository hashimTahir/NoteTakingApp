/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.models

data class Note(val hCreationDate:String,
                val hContents:String,
                val hUpVotes: Int,
                val hImageUrl: String,
                val hCreator: User?)