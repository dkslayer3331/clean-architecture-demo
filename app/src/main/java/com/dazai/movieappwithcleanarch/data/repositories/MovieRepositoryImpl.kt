package com.dazai.movieappwithcleanarch.data.repositories

import android.util.Log
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
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

    override suspend fun fetchMovies(): Flow<List<MovieEntity>> {
//        try {
//            api.getMovies().collect {
//                db.movieDao().addMovies(it.movies)
//            }
//        }catch (e : Exception){
//            Log.d("RepoErr",e.localizedMessage)
//        }
//        return db.movieDao().getMoviesDistinctUntilChanged()
        return flow {
            emit(api.getMovies().movies)
        }.flowOn(Dispatchers.IO)
            .flatMapMerge {
                db.movieDao().deleteAllMovies()
                db.movieDao().addMovies(it)
                return@flatMapMerge db.movieDao().getMoviesDistinctUntilChanged()
            }
    }

    override suspend fun refreshMovies() {
            fetchMovies()
    }

}