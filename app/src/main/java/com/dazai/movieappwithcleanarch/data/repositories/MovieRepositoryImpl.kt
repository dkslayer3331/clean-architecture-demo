package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.app.Resource
import com.dazai.movieappwithcleanarch.app.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.entities.DbMovieEntity
import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): Resource<List<DbMovieEntity>> {
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): Resource<List<DbMovieEntity>> {
        return try {
            db.movieDao().deleteAllMovies()
            db.movieDao().addMovies(api.getMovies().movies.map { it.toDbEntity() })
            Resource.Success(db.movieDao().getAllMovies())
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.cause!!).message)
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<DbMovieEntity> {
        return try {
            val movieDetail = api.getMovieDetail(id)
            db.movieDao().updateDetail(movieDetail.toDbEntity())
            Resource.Success(db.movieDao().getMovieDetail(id))
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

    private suspend fun getMoviesFromAvailableSource(): Resource<List<DbMovieEntity>> {
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