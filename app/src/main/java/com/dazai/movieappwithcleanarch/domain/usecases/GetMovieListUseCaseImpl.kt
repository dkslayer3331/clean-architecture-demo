package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import javax.inject.Inject

class GetMovieListUseCaseImpl @Inject constructor(
        private val errorUseCase: ErrorUseCase,
        private val repository: MovieRepository
) : GetMovieListUseCase {
    override suspend fun invoke(): Resource<List<Movie>> {
        return try {
            Resource.Success(repository.fetchMovies())
        }catch ( e : Exception){
            Resource.Error(errorUseCase.getErrorMessage(e))
        }
    }
}