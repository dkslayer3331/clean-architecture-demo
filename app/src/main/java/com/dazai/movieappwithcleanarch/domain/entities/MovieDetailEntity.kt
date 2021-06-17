package com.dazai.movieappwithcleanarch.domain.entities

import com.dazai.movieappwithcleanarch.data.models.Genre

data class MovieDetailEntity(
   val backDropPath : String,
   val genres : List<Genre>,
   val id : Int,
   val title : String,
   val overview : String,
   val posterPath : String,
   val voteAverage : Double
)