package com.example.theoneapp.ui.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theoneapp.model.BookResponse
import com.example.theoneapp.model.NetworkResponse
import com.example.theoneapp.repository.Repository
import kotlinx.coroutines.launch

class BooksViewModel(val repository: Repository) : ViewModel() {
    private val _booksResponse = MutableLiveData<BookResponse?>()
    val booksResponse: LiveData<BookResponse?> = _booksResponse
    private val _bookError = MutableLiveData<Unit>()
    val bookError: LiveData<Unit> = _bookError
    var loadingStateLiveData = MutableLiveData<State>()

    fun retrieveBooks() = viewModelScope.launch {
        loadingStateLiveData.value = State.LOADING
        when(val response = repository.retrieveBooks()){
            is NetworkResponse.Failed -> {
                _bookError.value = Unit
            }
            is NetworkResponse.Success -> {
                _booksResponse.value = response.data
            }
        }
        loadingStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING,
        LOADING_FINISHED
    }
}