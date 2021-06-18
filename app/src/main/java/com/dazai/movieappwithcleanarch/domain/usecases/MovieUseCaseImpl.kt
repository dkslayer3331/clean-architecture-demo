package com.dazai.movieappwithcleanarch.domain.usecases

import android.util.Log
import com.dazai.movieappwithcleanarch.data.mappers.MovieDetailMapper
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
        private val repository: MovieRepository,
        private val mapper: MovieMapper,
        private val movieDetailMapper : MovieDetailMapper
) : MovieUseCase {
    override suspend fun getMovies(): List<MovieEntity> {
        return mapper.toMovieList(repository.fetchMovies())
    }

    override suspend fun getHighRatedMovies(): List<MovieEntity> {
        return mapper.toMovieList(repository.fetchMovies().filter { it.vote > 4 })
    }

    override suspend fun refreshMovies(): List<MovieEntity> {
       return mapper.toMovieList(repository.refreshMovies())
    }

    override suspend fun getMovieDetail(id: Int): MovieDetailEntity {
       return movieDetailMapper.toEntity(repository.getMovieDetail(id))
    }
}