package com.dazai.movieappwithcleanarch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

   private val _viewState = MutableLiveData<Resource<Movie>>()

    val viewState : LiveData<Resource<Movie>>
        get() = _viewState

    private val _loadingEvent = MutableLiveData<Boolean>()

    val loadingEvent : LiveData<Boolean>
        get() = _loadingEvent

    private val _successEvent = MutableLiveData<Movie>()

    val successEvent : LiveData<Movie>
        get() = _successEvent

    private val _errorEvent = MutableLiveData<String>()

    val errorEvent : LiveData<String>
        get() = _errorEvent

    fun getMovieDetail(id : Int){
        viewModelScope.launch {
            _loadingEvent.value = true
           if(movieUseCase.getMovieDetail(id) is Resource.Success){
               _successEvent.value = movieUseCase.getMovieDetail(id).data ?: return@launch
           }
            else _errorEvent.value= movieUseCase.getMovieDetail(id).message ?: return@launch
        }
    }

}