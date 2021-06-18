package com.dazai.movieappwithcleanarch.data.repositories

import android.util.Log
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb
) : MovieRepository {

    override suspend fun fetchMovies(): List<MovieVO> {
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): List<MovieVO> {
        db.movieDao().deleteAllMovies()
        db.movieDao().addMovies(api.getMovies().movies)
        return db.movieDao().getAllMovies()
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailResponse = api.getMovieDetail(id)

    private suspend fun getMoviesFromAvailableSource(): List<MovieVO> {

        if(db.movieDao().getAllMovies().isEmpty()){
            db.movieDao().addMovies(api.getMovies().movies)
        }

        return db.movieDao().getAllMovies()
    }

}