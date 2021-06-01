package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUsecaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {
    override suspend fun getMovies(): Flow<List<MovieEntity>> {
     return repository.fetchMovies()
    }

    override suspend fun refreshMovies() {
        repository.refreshMovies()
    }
}