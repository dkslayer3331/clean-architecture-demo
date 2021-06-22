package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.ui.utils.toUseCaseEntity
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
        private val repository: MovieRepository,
        private val errorHandler : ErrorHandler
) : MovieUseCase {
    override suspend fun getMovies(): Resource<List<Movie>> {
        return try{
            Resource.Success(repository.fetchMovies())
        }catch ( e : Exception){
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }

    override suspend fun getMovieDetail(id: Int): Resource<Movie> {
        return try {
            Resource.Success(repository.getMovieDetail(id))
        }catch (e : Exception){
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }
}