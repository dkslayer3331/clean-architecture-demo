package com.dazai.movieappwithcleanarch.data.network
import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieResponseVO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/movie/popular")
    suspend fun getMovies(): MovieResponseVO

    @GET("/movie/{Id}")
    suspend fun getMovieDetail(@Path("Id") id : Int) : MovieDetailResponse

}