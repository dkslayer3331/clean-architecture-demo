package com.dazai.movieappwithcleanarch.data.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieResponse(
    @PrimaryKey
    @SerializedName("id")
    val id : Long,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("vote_average")
    val vote : Float
)