package com.dazai.movieappwithcleanarch.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dazai.movieappwithcleanarch.domain.entities.MovieDetailEntity
import com.dazai.movieappwithcleanarch.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    val viewState = MutableLiveData<Resource<MovieDetailEntity>>()

    fun getMovieDetail(id : Int){
        viewModelScope.launch {
            viewState.value = Resource.Loading()
            movieUseCase.getMovieDetail(id).catch {
                viewState.postValue(Resource.Error(it.localizedMessage))
            }
            .collect {
                viewState.postValue(Resource.Success(it))
            }
        }
    }

}