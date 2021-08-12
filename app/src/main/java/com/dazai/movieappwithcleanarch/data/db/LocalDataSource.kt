package com.dazai.movieappwithcleanarch.data.db

import com.dazai.movieappwithcleanarch.data.entities.MovieEntity

interface LocalDataSource {
    suspend fun getMovies() : List<MovieEntity>
    suspend fun getMovieDetail(id : Int) : MovieEntity
    suspend fun addMovie(movieEntity: MovieEntity)
    suspend fun addMovies(movies : List<MovieEntity>)
    suspend fun updateMovieDetail(movieId: Int, movieEntity: MovieEntity)
}