package com.dazai.movieappwithcleanarch.domain.repositories

import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity

interface MovieRepository {
   suspend fun fetchMovies() : Resource<List<MovieEntity>>
   suspend fun refreshMovies() : Resource<List<MovieEntity>>
   suspend fun getMovieDetail(id : Int) : Resource<MovieEntity>
}