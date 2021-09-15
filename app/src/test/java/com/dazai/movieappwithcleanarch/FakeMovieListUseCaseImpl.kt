package com.dazai.movieappwithcleanarch

import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource

class FakeMovieListUseCaseImpl : GetMovieListUseCase {

    private var errReturn = false

     fun setErrorReturn(){
        errReturn = true
    }

    fun resetErrorReturn(){
        errReturn = false
    }

    override suspend fun invoke(): Resource<List<Movie>> {
        if (errReturn) return Resource.Error("error something")
        return Resource.Success(getMockMovies())
    }
}