package com.dazai.movieappwithcleanarch.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

   private val _viewState = MutableLiveData<Resource<MovieDetailEntity>>()

    val viewState : LiveData<Resource<MovieDetailEntity>>
        get() = _viewState

    fun getMovieDetail(id : Int){
        viewModelScope.launch {
            _viewState.value = Resource.Loading()
            try {
                _viewState.value = movieUseCase.getMovieDetail(id)
            }catch ( e : Exception){
                _viewState.value = Resource.Error(e.localizedMessage)
            }
        }
    }

}