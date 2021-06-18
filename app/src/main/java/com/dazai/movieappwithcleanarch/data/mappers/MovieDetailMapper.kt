package com.dazai.movieappwithcleanarch.data.mappers

import com.dazai.movieappwithcleanarch.data.models.MovieDetailResponse
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : GenericMapper<MovieDetailEntity, MovieDetailResponse> {
    override fun toEntity(model: MovieDetailResponse): MovieDetailEntity {
        return MovieDetailEntity(
                id = model.id,
                title = model.title,
                overview = model.overview,
                voteAverage = model.voteAverage,
                genres = model.genres,
                posterPath = model.posterPath, // could be different image endpoint
                backDropPath = model.backdropPath
        )
    }

}