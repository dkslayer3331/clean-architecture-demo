package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getMovies() : Flow<List<MovieEntity>>
    suspend fun getHighRatedMovies() : Flow<List<MovieEntity>>
    suspend fun refreshMovies() : Flow<List<MovieEntity>>
    suspend fun getMovieDetail(id : Int)
}