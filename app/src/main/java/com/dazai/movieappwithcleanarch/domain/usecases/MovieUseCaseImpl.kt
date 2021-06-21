package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.ui.utils.Resource
import com.dazai.movieappwithcleanarch.ui.utils.toUseCaseEntity
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
        private val repository: MovieRepository
) : MovieUseCase {
    override suspend fun getMovies(): Resource<List<Movie>> {
       return repository.fetchMovies().data?.let {  movies ->
            Resource.Success(movies.map { it.toUseCaseEntity() })
        } ?: Resource.Error(repository.fetchMovies().message ?: "")
    }

    override suspend fun getHighRatedMovies(): Resource<List<Movie>> {
        return getMovies()
    }

    override suspend fun refreshMovies(): Resource<List<Movie>> {
       return repository.refreshMovies().data?.let {movies ->
          Resource.Success(movies.map { it.toUseCaseEntity() })
       } ?: Resource.Error(repository.refreshMovies().message ?: "")
    }

    override suspend fun getMovieDetail(id: Int): Resource<Movie> {
       return repository.getMovieDetail(id).data?.let {
           Resource.Success(it.toUseCaseEntity())
       } ?: Resource.Error(repository.getMovieDetail(id).message ?: "")
    }
}