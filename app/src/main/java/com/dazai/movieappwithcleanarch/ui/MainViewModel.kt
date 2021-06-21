package com.dazai.movieappwithcleanarch.ui

import androidx.lifecycle.*
import com.dazai.movieappwithcleanarch.domain.entities.Movie
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import com.dazai.movieappwithcleanarch.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val useCase: MovieUseCase
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
            when (useCase.getMovies()) {
                is Resource.Success -> {
                    _showMoviesEvent.value = useCase.getMovies().data ?: return@launch
                }
                else -> _showErrorEvent.value = useCase.getMovies().message ?: return@launch
            }
        }
    }

}