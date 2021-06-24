package com.dazai.movieappwithcleanarch.domain.usecases

interface ErrorUseCase {
    fun showToastErrorMessage(message : String)
}