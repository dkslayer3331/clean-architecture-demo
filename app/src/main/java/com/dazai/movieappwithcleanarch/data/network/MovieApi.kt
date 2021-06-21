package com.dazai.movieappwithcleanarch.data.network
import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular")
    suspend fun getMovies(): MovieListResponse

    @GET("movie/{Id}")
    suspend fun getMovieDetail(@Path("Id") id : Int) : MovieDetailResponse

}