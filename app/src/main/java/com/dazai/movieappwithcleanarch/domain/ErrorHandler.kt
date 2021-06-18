package com.dazai.movieappwithcleanarch.domain

import com.dazai.movieappwithcleanarch.data.utils.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable) : ErrorEntity
}