package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.ui.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.ui.utils.toUseCaseEntity
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): List<Movie> {
        try {
           val moviesFromRemote = api.getMovies()
            db.movieDao().addMovies(moviesFromRemote.movies.map { it.toDbEntity() })
        } catch (exception: Exception) {
            if(db.movieDao().getAllMovies().isEmpty()) throw exception
            return db.movieDao().getAllMovies().map { it.toUseCaseEntity() }
        }
        return db.movieDao().getAllMovies().map { it.toUseCaseEntity() }
    }

    override suspend fun getMovieDetail(id: Int): Movie {
        try {
            val movieDetail = api.getMovieDetail(id)
            db.movieDao().updateDetail(movieDetail.toDbEntity())
        } catch (exception: Exception) {
            if(db.movieDao().getMovieDetail(id) == null) throw exception
            return db.movieDao().getMovieDetail(id).toUseCaseEntity() // db as source of truth
        }
        return db.movieDao().getMovieDetail(id).toUseCaseEntity()
    }

}