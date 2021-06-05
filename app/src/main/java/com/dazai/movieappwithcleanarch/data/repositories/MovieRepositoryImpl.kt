package com.dazai.movieappwithcleanarch.data.repositories

import android.util.Log
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi, private val db: AppDb, private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun fetchMovies(): Flow<List<MovieVO>> {
        return flow {
            emit(api.getMovies().movies)
        }
            .flatMapMerge {
                db.movieDao().deleteAllMovies()
                db.movieDao().addMovies(it)
                return@flatMapMerge db.movieDao().getMoviesDistinctUntilChanged()
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun refreshMovies() {

    }

    private suspend fun getMoviesFromAvailableSource() : Flow<List<MovieVO>>{
            val cache = db.movieDao().getAllMovies()
            cache.collect {
                if(it.isEmpty()){
                    db.movieDao().addMovies(api.getMovies().movies)
                }
            }
        return db.movieDao().getAllMovies()

    }

}