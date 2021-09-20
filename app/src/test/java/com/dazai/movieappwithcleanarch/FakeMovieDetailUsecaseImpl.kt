package com.dazai.movieappwithcleanarch

import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieDetailUseCase
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource

class FakeMovieDetailUsecaseImpl : GetMovieDetailUseCase {

    private var _errStatus = false

    fun setErrStatus(status: Boolean){
        _errStatus = true
    }

    override suspend fun invoke(id: Int): Resource<Movie> {
        if(_errStatus) return Resource.Error("movie detail error")
        return Resource.Success(getMockedMovie())
    }
}