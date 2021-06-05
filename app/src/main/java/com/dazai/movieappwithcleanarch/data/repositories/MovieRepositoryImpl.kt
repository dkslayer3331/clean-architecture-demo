package com.dazai.movieappwithcleanarch.data.repositories

import android.util.Log
import com.dazai.movieappwithcleanarch.data.db.AppDb
import com.dazai.movieappwithcleanarch.data.mappers.MovieMapper
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.data.network.MovieApi
import com.dazai.movieappwithcleanarch.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi, private val db: AppDb, private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun fetchMovies(): Flow<List<MovieVO>> {
//        return flow {
//            emit(api.getMovies().movies)
//        }
//            .flatMapMerge {
//                db.movieDao().deleteAllMovies()
//                db.movieDao().addMovies(it)
//                return@flatMapMerge db.movieDao().getMoviesDistinctUntilChanged()
//            }
//            .flowOn(Dispatchers.IO)
        return getMoviesFromAvailableSource()
    }

    override suspend fun refreshMovies(): Flow<List<MovieVO>> {
        db.movieDao().deleteAllMovies()
        db.movieDao().addMovies(api.getMovies().movies)
        return db.movieDao().getAllMovies()
    }

    private suspend fun getMoviesFromAvailableSource() : Flow<List<MovieVO>>{
            val cache = db.movieDao().getAllMovies()
            cache.collect {
                if(it.isEmpty()){
                    Log.d("EmptyCache","fetch from network")
                    db.movieDao().addMovies(api.getMovies().movies)
                }
            }
        return db.movieDao().getAllMovies()

    }

}