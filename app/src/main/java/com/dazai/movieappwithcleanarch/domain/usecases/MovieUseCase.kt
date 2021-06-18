package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.app.Resource
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity

interface MovieUseCase {
    suspend fun getMovies() : Resource<List<MovieEntity>>
    suspend fun getHighRatedMovies() : Resource<List<MovieEntity>>
    suspend fun refreshMovies() : Resource<List<MovieEntity>>
    suspend fun getMovieDetail(id : Int) : Resource<MovieDetailEntity>
}