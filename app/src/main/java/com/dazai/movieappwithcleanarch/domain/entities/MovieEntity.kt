package com.dazai.movieappwithcleanarch.domain.entities

data class MovieEntity(
    val id : Int,
   val originalTitle : String,
   val posterPath : String,
   val title : String
)