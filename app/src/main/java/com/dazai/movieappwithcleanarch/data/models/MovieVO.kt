package com.dazai.movieappwithcleanarch.data.models

import com.google.gson.annotations.SerializedName

data class MovieVO(
    @SerializedName("id")
    val id : Long,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("title")
    val title : String,
)