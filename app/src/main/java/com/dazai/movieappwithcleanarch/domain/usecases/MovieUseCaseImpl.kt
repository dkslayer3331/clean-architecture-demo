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
    override suspend fun getMovies(): Flow<List<MovieEntity>> {
        return repository.fetchMovies().map{
            mapper.toMovieList(it)
        }
    }

    override suspend fun getHighRatedMovies(): Flow<List<MovieEntity>> {
        return repository.fetchMovies().map {
            mapper.toMovieList(it.filter { it.vote > 4 })
        }
    }

    override suspend fun refreshMovies(): Flow<List<MovieEntity>> {
       return repository.refreshMovies().map {
           mapper.toMovieList(it)
       }
    }

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetailEntity> {
       return repository.getMovieDetail(id).map {
           movieDetailMapper.toEntity(it)
       }
    }
}