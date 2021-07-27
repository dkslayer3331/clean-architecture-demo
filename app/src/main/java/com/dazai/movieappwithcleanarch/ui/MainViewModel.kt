package com.dazai.movieappwithcleanarch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazai.movieappwithcleanarch.domain.model.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _showMoviesEvent = MutableLiveData<List<Movie>>()

    val showMoviesEvent: LiveData<List<Movie>>
        get() = _showMoviesEvent

    private val _showLoadingEvent = MutableLiveData<Boolean>()

    val showLoadingEvent: LiveData<Boolean>
        get() = _showLoadingEvent

    private val _showErrorEvent = MutableLiveData<String>()

    val showErrorEvent: LiveData<String>
        get() = _showErrorEvent

    init {
        showMovies()
    }

    private fun showMovies() {
        viewModelScope.launch {
            _showLoadingEvent.value = true
            when (getMovieListUseCase()) {
                is Resource.Success -> {
                    _showLoadingEvent.value = false
                    _showMoviesEvent.value = getMovieListUseCase().data ?: return@launch
                }
                else -> {
                    _showLoadingEvent.value = false
                    _showErrorEvent.value = getMovieListUseCase().message ?: return@launch
                }
            }
        }
    }

}