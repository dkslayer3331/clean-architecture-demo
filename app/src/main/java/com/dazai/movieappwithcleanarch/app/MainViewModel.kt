package com.dazai.movieappwithcleanarch.app

import androidx.lifecycle.*
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel constructor(
    private val useCase: MovieUseCase
) : ViewModel(){

    val movies  = MutableLiveData<List<MovieEntity>>()

    init {
       showMovies()
    }

    private fun showMovies(){
        viewModelScope.launch {
            useCase.getMovies().map {
                movies.postValue(it)
            }
        }
    }


}