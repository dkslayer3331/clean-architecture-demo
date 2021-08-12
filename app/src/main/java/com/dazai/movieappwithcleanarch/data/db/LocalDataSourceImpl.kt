package com.dazai.movieappwithcleanarch.data.db

import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDb: AppDb
) : LocalDataSource {

    override suspend fun getMovies(): List<MovieEntity> {
        return appDb.movieDao().getAllMovies()
    }

    override suspend fun getMovieDetail(id: Int): MovieEntity {
        return appDb.movieDao().getMovieDetail(id)
    }

    override suspend fun addMovie(movieEntity: MovieEntity) {
        appDb.movieDao().addMovie(movieEntity)
    }

    override suspend fun addMovies(movies: List<MovieEntity>) {
        appDb.movieDao().addMovies(movies)
    }

    override suspend fun updateMovieDetail(movieId: Int, movieEntity: MovieEntity) {
        appDb.movieDao().updateMovieDetail(movieId, movieEntity)
    }
}