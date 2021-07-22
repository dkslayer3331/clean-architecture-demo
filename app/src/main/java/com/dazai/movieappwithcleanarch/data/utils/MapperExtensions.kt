package com.dazai.movieappwithcleanarch.data.utils

import android.util.Log
import com.dazai.movieappwithcleanarch.data.entities.MovieEntity
import com.dazai.movieappwithcleanarch.data.responses.MovieDetailResponse
import com.dazai.movieappwithcleanarch.data.responses.MovieResponse
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.ui.utils.IMAGE_ENDPOINT

/** response to db entity **/

fun MovieResponse.toDbEntity() = with(this) {
    MovieEntity(
            id = id.toInt(),
            title = title,
            originalTitle = originalTitle,
            voteAverage = vote,
            posterPath = posterPath,
            genres = emptyList(),
            overview = ""
    )
}

fun MovieDetailResponse.toDbEntity() = with(this){
    Log.d("networkResponse", "$this")
    MovieEntity(
            id = id,
            title = title,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            backDropPath = backdropPath,
            genres = genres,
            voteAverage = voteAverage.toFloat(),
            releaseDate = releaseDate
    )
}

/** db entity to use case models **/
fun MovieEntity.toUseCaseEntity() = with(this){
    Log.d("mapperToUseCase","$overview")
    Movie(
            id, originalTitle, IMAGE_ENDPOINT + posterPath, title, overview ?: "",
            genres?.joinToString { it.name } ?: "", voteAverage, releaseDate ?: ""
    )
}
