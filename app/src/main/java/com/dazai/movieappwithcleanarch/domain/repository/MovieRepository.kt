package com.dazai.movieappwithcleanarch.domain.repository

interface MovieRepository {
    fun fetchMovies() // todo : inject data sources as param
}