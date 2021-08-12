package com.dazai.movieappwithcleanarch.data.network

import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse

interface NetworkDataSource {
    suspend fun getMovieList() : List<MovieResponse>
    suspend fun getMovieDetail(id : Int) : MovieDetailResponse
}