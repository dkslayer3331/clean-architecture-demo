package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.app.Resource
import com.dazai.movieappwithcleanarch.app.utils.toEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
        private val repository: MovieRepository
) : MovieUseCase {
    override suspend fun getMovies(): Resource<List<MovieEntity>> {
       return repository.fetchMovies().data?.let {  movies ->
            Resource.Success(movies.map { it.toEntity() })
        } ?: Resource.Error(repository.fetchMovies().message ?: "")
    }

    override suspend fun getHighRatedMovies(): Resource<List<MovieEntity>> {
        return getMovies()
    }

    override suspend fun refreshMovies(): Resource<List<MovieEntity>> {
       return repository.refreshMovies().data?.let {movies ->
          Resource.Success(movies.map { it.toEntity() })
       } ?: Resource.Error(repository.refreshMovies().message ?: "")
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetailEntity> {
       return repository.getMovieDetail(id).data?.let {
           Resource.Success(it.toEntity())
       } ?: Resource.Error(repository.getMovieDetail(id).message ?: "")
    }
}