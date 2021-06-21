package com.dazai.movieappwithcleanarch.data.db

import androidx.room.*
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 3, exportSchema = false)
abstract class AppDb : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}

@Dao
interface MovieDao{

    @Query("select id, title, originalTitle, posterPath, voteAverage  from movie")
    suspend fun getAllMovies() : List<com.dazai.movieappwithcleanarch.data.entities.MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies : List<com.dazai.movieappwithcleanarch.data.entities.MovieEntity>)

    @Query("delete from movie")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDetail(movie : com.dazai.movieappwithcleanarch.data.entities.MovieEntity)

    @Query("select * from movie where id = :movieId")
    suspend fun getMovieDetail(movieId : Int) : com.dazai.movieappwithcleanarch.data.entities.MovieEntity

}