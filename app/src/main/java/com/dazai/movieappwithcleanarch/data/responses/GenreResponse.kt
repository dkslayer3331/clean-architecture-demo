package com.dazai.movieappwithcleanarch.data.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)