package com.dazai.movieappwithcleanarch.data.db

import androidx.room.*
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Database(entities = [MovieVO::class], version = 1)
abstract class AppDb : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}

@Dao
interface MovieDao{

    @Query("select * from movies")
    suspend fun getAllMovies() : Flow<List<MovieEntity>>

    @Insert
    suspend fun addMovies(movies : List<MovieVO>)

    suspend fun getMoviesDistinctUntilChanged() = getAllMovies().distinctUntilChanged()

}