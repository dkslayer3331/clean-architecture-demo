package com.dazai.movieappwithcleanarch.data.network

import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : NetworkDataSource {

    override suspend fun getMovieList(): List<MovieResponse> {
        return movieApi.getMovies().movies
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailResponse {
        return movieApi.getMovieDetail(id)
    }

}