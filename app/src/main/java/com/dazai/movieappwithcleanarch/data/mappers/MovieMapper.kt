package com.dazai.movieappwithcleanarch.data.mappers

import com.dazai.movieappwithcleanarch.data.models.MovieResponseVO
import com.dazai.movieappwithcleanarch.data.models.MovieVO
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.fullImageUrl

class MovieMapper : GenericMapper<MovieEntity, MovieVO>{
    override fun toEntity(model: MovieVO): MovieEntity {
        return MovieEntity(
            originalTitle = model.originalTitle,
            posterPath = model.posterPath.fullImageUrl(),
            title = model.title
        )
        }

    fun toMovieList(response : MovieResponseVO) : List<MovieEntity>{
        return response.movies.map { toEntity(it) }
    }
}