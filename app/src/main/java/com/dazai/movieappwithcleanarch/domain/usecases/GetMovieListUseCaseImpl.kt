package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import javax.inject.Inject

class GetMovieListUseCaseImpl @Inject constructor(
        private val errorHandler: ErrorHandler,
        private val repository: MovieRepository
) : GetMovieListUseCase {
    override suspend fun invoke(): Resource<List<Movie>> {
        return try {
            Resource.Success(repository.fetchMovies())
        }catch ( e : Exception){
            Resource.Error(errorHandler.getError(e.fillInStackTrace()).message)
        }
    }
}