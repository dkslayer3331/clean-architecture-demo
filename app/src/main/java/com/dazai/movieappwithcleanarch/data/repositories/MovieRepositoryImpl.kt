package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.app.Resource
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
        private val api: MovieApi, private val db: AppDb, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): Resource<List<MovieVO>> {
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): Resource<List<MovieVO>> {
        return try {
            db.movieDao().deleteAllMovies()
            db.movieDao().addMovies(api.getMovies().movies)
            Resource.Success(db.movieDao().getAllMovies())
        } catch (e: Exception) {
            Resource.Error(errorHandler.getError(e.cause!!).message)
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetailResponse>{
        return try {
             Resource.Success(api.getMovieDetail(id))
        }catch (e : Exception){
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

    private suspend fun getMoviesFromAvailableSource(): Resource<List<MovieVO>> {
        return try{
            if (db.movieDao().getAllMovies().isEmpty()) {
                db.movieDao().addMovies(api.getMovies().movies)
            }
             Resource.Success(db.movieDao().getAllMovies())
        }catch (e : Exception){
             Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

}