package com.dazai.movieappwithcleanarch.domain.entities

data class Movie(
        val id: Int,
        val originalTitle: String,
        val posterPath: String,
        val title: String,
        val overview: String = "",
        val genres: String = "",
        val voteAverage: Float = 0F
)