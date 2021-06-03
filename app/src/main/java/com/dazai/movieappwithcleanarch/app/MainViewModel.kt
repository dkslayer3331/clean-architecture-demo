package com.dazai.movieappwithcleanarch.app

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
) : ViewModel(){

    val movies  = MutableLiveData<List<MovieEntity>>()

    val viewState = MutableLiveData<Result>()

    val errorMessage = MutableLiveData<String>("")

    init {
       showMovies()
    }

     private fun showMovies(){
        viewModelScope.launch {
            viewState.postValue(Result.Loading)
            useCase.getMovies().catch {
                viewState.postValue(Result.Error(it.localizedMessage))
                errorMessage.postValue(it.localizedMessage)
            }.collect {
                movies.postValue(it)
                viewState.postValue(Result.Success(it))
            }

        }
    }

}