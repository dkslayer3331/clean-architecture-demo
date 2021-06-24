package com.dazai.movieappwithcleanarch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.GetMovieListUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val useCase: GetMovieListUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<Resource<List<Movie>>>()

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
            when (useCase()) {
                is Resource.Success -> {
                    _showMoviesEvent.value = useCase().data ?: return@launch
                }
                else -> _showErrorEvent.value = useCase().message ?: return@launch
            }
        }
    }

}