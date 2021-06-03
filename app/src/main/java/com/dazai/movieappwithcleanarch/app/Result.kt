package com.dazai.movieappwithcleanarch.app

import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity

sealed class Result{
    object Loading : Result()
    data class Success(val data : List<MovieEntity>) : Result()
    data class Error(val message : String) : Result()
}