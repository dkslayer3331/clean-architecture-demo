package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.domain.entities.Movie

interface MovieUseCase {
    suspend fun getMovies() : Resource<List<Movie>>
    suspend fun getHighRatedMovies() : Resource<List<Movie>>
    suspend fun refreshMovies() : Resource<List<Movie>>
    suspend fun getMovieDetail(id : Int) : Resource<Movie>
}