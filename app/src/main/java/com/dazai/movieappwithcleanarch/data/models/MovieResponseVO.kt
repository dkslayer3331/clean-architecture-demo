package com.dazai.movieappwithcleanarch.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponseVO(
    @SerializedName("results")
    val movies : List<MovieVO>
)