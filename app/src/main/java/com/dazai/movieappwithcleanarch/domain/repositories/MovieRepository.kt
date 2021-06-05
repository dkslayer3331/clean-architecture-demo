package com.dazai.movieappwithcleanarch.domain.repositories

import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun fetchMovies() : Flow<List<MovieVO>>
   suspend fun refreshMovies()
}