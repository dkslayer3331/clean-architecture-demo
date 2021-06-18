package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.app.Resource
import com.dazai.movieappwithcleanarch.data.mappers.MovieDetailMapper
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
        private val repository: MovieRepository,
        private val mapper: MovieMapper,
        private val movieDetailMapper : MovieDetailMapper
) : MovieUseCase {
    override suspend fun getMovies(): Resource<List<MovieEntity>> {
       return repository.fetchMovies().data?.let {
            Resource.Success(mapper.toMovieList(it))
        } ?: Resource.Error(repository.fetchMovies().message ?: "")
    }

    override suspend fun getHighRatedMovies(): Resource<List<MovieEntity>> {
        return getMovies()
    }

    override suspend fun refreshMovies(): Resource<List<MovieEntity>> {
       return repository.refreshMovies().data?.let {
          Resource.Success(mapper.toMovieList(it))
       } ?: Resource.Error(repository.refreshMovies().message ?: "")
    }

    override suspend fun getMovieDetail(id: Int): Resource<MovieDetailEntity> {
       return repository.getMovieDetail(id).data?.let {
           Resource.Success(movieDetailMapper.toEntity(it))
       } ?: Resource.Error(repository.getMovieDetail(id).message ?: "")
    }
}