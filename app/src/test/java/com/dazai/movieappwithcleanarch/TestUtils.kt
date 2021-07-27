package com.dazai.movieappwithcleanarch

import com.dazai.movieappwithcleanarch.domain.model.Movie

fun getMockMovies() : List<Movie>{
    val list = mutableListOf<Movie>()
    for(i in 1..10){
        list.add(Movie(
           id = i,
           title = "title$i",
                originalTitle = "originalTitle$i",
                voteAverage = 3.5f,
                posterPath = "posterPath$i",
                genres = "action, thriller, mystery",
                overview = "overview$i",
                releaseYear = "year$i"
        ))
    }
    return list
}