package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) : MovieUseCase {
    override suspend fun getMovies(): Flow<List<MovieEntity>> {
     return repository.fetchMovies().map {
         mapper.toMovieList(it)
     }
    }

    override suspend fun refreshMovies() {
        repository.refreshMovies()
    }
}