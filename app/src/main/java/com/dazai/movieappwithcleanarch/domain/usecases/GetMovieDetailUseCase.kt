package com.dazai.movieappwithcleanarch.domain.usecases

import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.ui.utils.Resource

interface GetMovieDetailUseCase {
    suspend operator fun invoke(id : Int) : Resource<Movie>
}