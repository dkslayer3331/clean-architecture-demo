package com.dazai.movieappwithcleanarch.data.mappers

import com.dazai.movieappwithcleanarch.data.models.MovieResponseVO
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.fullImageUrl
import javax.inject.Inject

class MovieMapper @Inject constructor() : GenericMapper<MovieEntity, MovieVO>{

    override fun toEntity(model: MovieVO): MovieEntity {
        return MovieEntity(
            id = model.id.toInt(),
            originalTitle = model.originalTitle,
            posterPath = model.posterPath.fullImageUrl(),
            title = model.title
        )
        }

    fun toMovieList(list : List<MovieVO>) : List<MovieEntity>{
        return list.map { toEntity(it) }
    }
}