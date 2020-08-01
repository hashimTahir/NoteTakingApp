/*
 * Copyright (c) 2020/  8/ 2.  Created by Hashim Tahir
 */

package com.hashim.noteapp.models

sealed class ResultResponse<out E, out V> {
    data class Value<out V>(val value: V) : ResultResponse<Nothing, V>()
    data class Error<out E>(val error: E) : ResultResponse<E, Nothing>() {

        companion object Factory{
            inline fun <V> build(function: () -> V): ResultResponse<Exception, V> =
                try {
                    Value(function.invoke())
                } catch (e: java.lang.Exception) {
                    Error(e)
                }
        }
    }
}