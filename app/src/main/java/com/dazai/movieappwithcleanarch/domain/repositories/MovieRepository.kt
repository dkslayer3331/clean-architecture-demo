package com.dazai.movieappwithcleanarch.domain.repositories

import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
   suspend fun fetchMovies() : List<MovieVO>
   suspend fun refreshMovies() : List<MovieVO>
   suspend fun getMovieDetail(id : Int) : MovieDetailResponse
}