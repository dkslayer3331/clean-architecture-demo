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
        @Nullable
        val backDropPath : String? = "",
        @Nullable
        val genres : List<GenreResponse>? = emptyList(),
        val originalTitle : String,
        val title : String,
        @Nullable
        val overview : String? = "",
        val posterPath : String,
        val voteAverage : Float
)