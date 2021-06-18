package com.dazai.movieappwithcleanarch.app

import android.util.Log
import androidx.lifecycle.*
import com.dazai.movieappwithcleanarch.domain.entities.MovieEntity
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val useCase: MovieUseCase
) : ViewModel() {

   private val _viewState = MutableLiveData<Resource<List<MovieEntity>>>()

    val viewState : LiveData<Resource<List<MovieEntity>>>
        get() = _viewState

    init {
        showMovies()
    }

    private fun showMovies() {
        viewModelScope.launch {
            _viewState.value = Resource.Loading()
            try {
                _viewState.value = Resource.Success(useCase.getMovies())
            }catch (e : Exception){
                _viewState.value = Resource.Error(e.localizedMessage)
            }
        }
    }

}