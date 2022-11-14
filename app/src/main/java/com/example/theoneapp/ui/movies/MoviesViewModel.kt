package com.example.theoneapp.ui.movies

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theoneapp.model.MovieResponse
import com.example.theoneapp.model.NetworkResponse
import com.example.theoneapp.repository.Repository
import kotlinx.coroutines.launch

class MoviesViewModel(val repository: Repository) : ViewModel() {
    private val _moviesResponse = MutableLiveData<MovieResponse?>()
    val moviesResponse: LiveData<MovieResponse?> = _moviesResponse
    private val _movieError = MutableLiveData<Unit>()
    val movieError: LiveData<Unit> = _movieError
    var loadingStateLiveData = MutableLiveData<State>()

    fun retrieveMovies() = viewModelScope.launch {
        loadingStateLiveData.value = State.LOADING
        when (val response = repository.retrieveMovie()) {
            is NetworkResponse.Failed -> {
                _movieError.value = Unit
            }
            is NetworkResponse.Success -> {
                _moviesResponse.value = response.data
            }
        }
        loadingStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING,
        LOADING_FINISHED
    }
}