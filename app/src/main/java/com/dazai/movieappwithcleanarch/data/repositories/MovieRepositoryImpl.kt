package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.ui.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): Resource<List<MovieEntity>> {
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): Resource<List<MovieEntity>> {
        return try {
            db.movieDao().deleteAllMovies()
            db.movieDao().addMovies(api.getMovies().movies.map { it.toDbEntity() })
            Resource.Success(db.movieDao().getAllMovies())
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieEntity> {
        return try {
            val movieDetail = api.getMovieDetail(id)
            db.movieDao().updateDetail(movieDetail.toDbEntity())
            Resource.Success(db.movieDao().getMovieDetail(id))
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

    private suspend fun getMoviesFromAvailableSource(): Resource<List<MovieEntity>> {
        return try {
            if (db.movieDao().getAllMovies().isEmpty()) {
                db.movieDao().addMovies(api.getMovies().movies.map { it.toDbEntity() })
            }
            Resource.Success(db.movieDao().getAllMovies())
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

}