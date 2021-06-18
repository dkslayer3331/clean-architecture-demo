package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getMovies() : List<MovieEntity>
    suspend fun getHighRatedMovies() : List<MovieEntity>
    suspend fun refreshMovies() : List<MovieEntity>
    suspend fun getMovieDetail(id : Int) : MovieDetailEntity
}