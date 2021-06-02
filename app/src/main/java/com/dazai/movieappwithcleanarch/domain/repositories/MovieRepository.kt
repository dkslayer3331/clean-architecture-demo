package com.dazai.movieappwithcleanarch.domain.repositories

import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun fetchMovies() : Flow<List<MovieEntity>>
   suspend fun refreshMovies()
}