package com.dazai.movieappwithcleanarch.data.db

import androidx.room.*
import com.dazai.movieappwithcleanarch.data.models.MovieVO

@Database(entities = [MovieVO::class], version = 1)
abstract class AppDb : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}

@Dao
interface MovieDao{

    @Query("select * from movie")
    fun getAllMovies() : List<MovieVO>

    @Insert
    fun addMovies(movies : List<MovieVO>)


}