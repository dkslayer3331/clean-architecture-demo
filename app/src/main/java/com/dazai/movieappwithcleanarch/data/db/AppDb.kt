package com.dazai.movieappwithcleanarch.data.db

import androidx.room.*
import com.dazai.movieappwithcleanarch.data.entities.DbMovieEntity
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Database(entities = [DbMovieEntity::class], version = 3, exportSchema = false)
abstract class AppDb : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}

@Dao
interface MovieDao{

    @Query("select id, title, originalTitle, posterPath, voteAverage  from movie")
    suspend fun getAllMovies() : List<DbMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies : List<DbMovieEntity>)

    @Query("delete from movie")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDetail(movie : DbMovieEntity)

    @Query("select * from movie where id = :movieId")
    suspend fun getMovieDetail(movieId : Int) : DbMovieEntity

}