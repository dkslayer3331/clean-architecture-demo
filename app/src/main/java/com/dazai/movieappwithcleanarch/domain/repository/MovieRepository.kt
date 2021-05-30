package com.dazai.movieappwithcleanarch.domain.repository

interface MovieRepository {
    fun fetchMovies(repo : MovieRepository)
}