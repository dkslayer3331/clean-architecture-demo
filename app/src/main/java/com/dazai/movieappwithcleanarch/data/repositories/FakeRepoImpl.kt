package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository

class FakeRepoImpl : MovieRepository {

    private var returnError = false

    fun setErrorReturn(){
        returnError = true
    }

    fun resetErrStatus(){
        returnError = false
    }

    override suspend fun fetchMovies(): List<Movie> {
        if(returnError) return emptyList()
        return (1..10).map { i-> Movie(
            id = i,
            title = "title$i",
            originalTitle = "originalTitle$i",
            voteAverage = 3.5f,
            posterPath = "posterPath$i",
            genres = "action, thriller, mystery",
            overview = "overview$i",
            releaseYear = "year$i"
        ) }
    }

    override suspend fun getMovieDetail(id: Int): Movie {
       return Movie(
           id = id,
           title = "title$id",
           originalTitle = "originalTitle$id",
           voteAverage = 3.5f,
           posterPath = "posterPath$id",
           genres = "action, thriller, mystery",
           overview = "overview$id",
           releaseYear = "year$id"
       )
    }
}