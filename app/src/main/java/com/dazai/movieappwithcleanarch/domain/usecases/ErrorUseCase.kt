package com.dazai.movieappwithcleanarch.domain.usecases

interface ErrorUseCase {
    fun getErrorMessage(e : Exception) : String
}