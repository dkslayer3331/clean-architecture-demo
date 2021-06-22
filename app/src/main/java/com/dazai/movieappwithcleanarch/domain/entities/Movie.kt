package com.dazai.movieappwithcleanarch.domain.entities

import com.dazai.movieappwithcleanarch.data.responses.GenreResponse

data class Movie(
        val id: Int,
        val originalTitle: String,
        val posterPath: String,
        val title: String,
        val overview: String = "",
        val genres: List<GenreResponse> = emptyList(),
        val voteAverage: Float = 0F
)