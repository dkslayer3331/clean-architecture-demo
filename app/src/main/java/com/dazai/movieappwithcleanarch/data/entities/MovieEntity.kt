package com.dazai.movieappwithcleanarch.data.entities

import androidx.annotation.Nullable
import androidx.room.*
import com.dazai.movieappwithcleanarch.data.db.GenreTypeConverter
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse

//  database entity
@Entity(tableName = "movie")
@TypeConverters(GenreTypeConverter::class)
data class MovieEntity(
        @PrimaryKey
        val id : Int,
        val backDropPath : String?,
        val genres : List<GenreResponse>?,
        val originalTitle : String,
        val title : String,
        val overview : String?,
        val posterPath : String,
        val voteAverage : Float,
        val releaseDate : String?
)