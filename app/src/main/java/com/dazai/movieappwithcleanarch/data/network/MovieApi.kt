package com.dazai.movieappwithcleanarch.data.network
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Any //todo : movie vo here
}