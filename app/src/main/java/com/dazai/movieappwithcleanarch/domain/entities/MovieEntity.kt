package com.dazai.movieappwithcleanarch.domain.entities

import com.dazai.movieappwithcleanarch.data.models.Genre

data class MovieEntity(
        val id: Int,
        val originalTitle: String,
        val posterPath: String,
        val title: String,
        val overview: String = "",
        val genres: List<Genre> = emptyList(),
        val voteAverage: Float = 0F
)