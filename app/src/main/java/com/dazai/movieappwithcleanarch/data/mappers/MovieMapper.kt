package com.dazai.movieappwithcleanarch.data.mappers

import com.dazai.movieappwithcleanarch.app.utils.IMAGE_ENDPOINT
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import javax.inject.Inject

class MovieMapper @Inject constructor() : GenericMapper<MovieEntity, MovieVO>{

    override fun toEntity(model: MovieVO): MovieEntity = with(model) {
        return MovieEntity(
            id = id.toInt(),
            originalTitle = originalTitle,
            posterPath = "$IMAGE_ENDPOINT${posterPath}",
            title = title
        )
        }

    fun toMovieList(list : List<MovieVO>) : List<MovieEntity>{
        return list.map { toEntity(it) }
    }
}