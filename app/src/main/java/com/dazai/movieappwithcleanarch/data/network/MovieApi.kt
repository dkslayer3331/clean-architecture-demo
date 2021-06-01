package com.dazai.movieappwithcleanarch.data.network
import com.dazai.movieappwithcleanarch.data.models.MovieResponseVO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular")
    suspend fun getMovies(): Flow<MovieResponseVO>
}