package com.dazai.movieappwithcleanarch.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val id : Long,
    @Json(name = "poster_path")
    val posterPath : String,
    @Json(name = "original_title")
    val originalTitle : String,
    val title : String,
    @Json(name = "vote_average")
    val vote : Float
)