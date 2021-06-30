package com.dazai.movieappwithcleanarch.domain.repositories

import com.dazai.movieappwithcleanarch.domain.model.Movie

interface MovieRepository {
   suspend fun fetchMovies() : List<Movie>
   //suspend fun refreshMovies() : List<Movie>
   suspend fun getMovieDetail(id : Int) : Movie
}