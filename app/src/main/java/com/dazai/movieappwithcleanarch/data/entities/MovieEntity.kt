package com.dazai.movieappwithcleanarch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dazai.movieappwithcleanarch.data.db.GenreTypeConverter
import com.dazai.movieappwithcleanarch.data.responses.GenreResponse
import org.jetbrains.annotations.Nullable

//  database entity
@Entity(tableName = "movie")
@TypeConverters(GenreTypeConverter::class)
data class MovieEntity(
        @PrimaryKey
        val id : Int,
        @Nullable
        val backDropPath : String? = "",
        @Nullable
        val genres : List<GenreResponse> = emptyList(),
        val originalTitle : String,
        val title : String,
        @Nullable
        val overview : String? = "",
        val posterPath : String,
        val voteAverage : Float
)