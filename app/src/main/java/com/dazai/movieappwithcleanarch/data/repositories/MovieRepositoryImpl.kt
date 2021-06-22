package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.ui.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.ui.utils.toUseCaseEntity
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): List<Movie> {
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): List<Movie> {
        return try {
            db.movieDao().deleteAllMovies()
            db.movieDao().addMovies(api.getMovies().movies.map { it.toDbEntity() })
            db.movieDao().getAllMovies().map { it.toUseCaseEntity() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMovieDetail(id: Int): Movie {
        return try {
            val movieDetail = api.getMovieDetail(id)
            db.movieDao().updateDetail(movieDetail.toDbEntity())
            db.movieDao().getMovieDetail(id).toUseCaseEntity()
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun getMoviesFromAvailableSource(): List<Movie> {
        return try {
            if (db.movieDao().getAllMovies().isEmpty()) {
                db.movieDao().addMovies(api.getMovies().movies.map { it.toDbEntity() })
            }
            db.movieDao().getAllMovies().map { it.toUseCaseEntity() }
        } catch (e: Exception) {
            throw e
        }
    }

}