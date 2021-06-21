package com.dazai.movieappwithcleanarch.data.responses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val movies : List<MovieResponse> = emptyList()
)