package com.dazai.movieappwithcleanarch.data.repositories

import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.db.LocalDataSource
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.data.network.NetworkDataSource
import com.dazai.movieappwithcleanarch.data.utils.toDbEntity
import com.dazai.movieappwithcleanarch.data.utils.toUseCaseEntity
import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: NetworkDataSource, private val cache: LocalDataSource, private val errorHandler: ErrorHandler
) : MovieRepository {

    override suspend fun fetchMovies(): List<Movie> {
        try {
           val moviesFromRemote = remote.getMovieList()
            cache.addMovies(moviesFromRemote.map { it.toDbEntity() })
        } catch (exception: Exception) {
            if(cache.getMovies().isEmpty()) throw exception
            return cache.getMovies().map { it.toUseCaseEntity() }
        }
        return cache.getMovies().map { it.toUseCaseEntity() }
    }

    override suspend fun getMovieDetail(id: Int): Movie {
        try {
            val movieDetail = remote.getMovieDetail(id)
            cache.updateMovieDetail(id, movieDetail.toDbEntity())
        } catch (exception: Exception) {
            if(cache.getMovieDetail(id) == null) throw exception
            return cache.getMovieDetail(id).toUseCaseEntity() // db as source of truth
        }
        return cache.getMovieDetail(id).toUseCaseEntity()
    }

}