package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.ui.utils.Resource

interface GetMovieListUseCase {
    suspend operator fun invoke() : Resource<List<Movie>>
}