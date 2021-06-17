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

    val viewState = MutableLiveData<Resource<List<MovieEntity>>>()

    init {
        showMovies()
    }

    private fun showMovies() {
        viewModelScope.launch {
            viewState.postValue(Resource.Loading())
            useCase.getMovies().catch {
                Log.d("ViewModelError",it.localizedMessage)
                viewState.postValue(Resource.Error(it.localizedMessage))
            }.collect {
                Log.d("ViewModelMovies",it.size.toString())
                viewState.postValue(Resource.Success(it))
            }
        }
    }

}