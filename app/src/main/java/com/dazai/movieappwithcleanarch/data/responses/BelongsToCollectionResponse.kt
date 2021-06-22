package com.dazai.movieappwithcleanarch.data.responses


import com.google.gson.annotations.SerializedName

data class BelongsToCollectionResponse(
    @SerializedName("backdrop_path")
    val backdropPath: Any?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: Any?
)