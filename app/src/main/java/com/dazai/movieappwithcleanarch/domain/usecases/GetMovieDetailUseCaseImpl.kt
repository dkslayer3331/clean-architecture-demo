package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.ErrorHandler
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
        private val repository: MovieRepository,
        private val errHandler : ErrorHandler
) : GetMovieDetailUseCase {
    override suspend fun invoke(id: Int): Resource<Movie> {
        return try {
            Resource.Success(repository.getMovieDetail(id))
        }catch ( e : Exception){
            Resource.Error(errHandler.getError(e.fillInStackTrace()).message)
        }
    }
}