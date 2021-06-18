package com.dazai.movieappwithcleanarch.data.mappers

import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : GenericMapper<MovieDetailEntity, MovieDetailResponse> {
    override fun toEntity(model: MovieDetailResponse): MovieDetailEntity = with(model) {
        return MovieDetailEntity(
                id = id,
                title = title,
                overview = overview,
                voteAverage = voteAverage,
                genres = genres,
                posterPath = posterPath, // could be different image endpoint
                backDropPath = backdropPath
        )
    }

}