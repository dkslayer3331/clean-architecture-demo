package com.dazai.movieappwithcleanarch.domain.usecases

interface MovieUseCase {
    suspend fun showMovies()
    suspend fun refreshMovies()
}