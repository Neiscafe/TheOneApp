package com.example.theoneapp.ui.books.bookDescription

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theoneapp.model.chapter.ChapterResponse
import com.example.theoneapp.model.NetworkResponse
import com.example.theoneapp.repository.Repository
import kotlinx.coroutines.launch

class BookDescriptionViewModel(val repository: Repository) : ViewModel() {

    private val _bookChapterResponse = MutableLiveData<ChapterResponse?>()
    val bookChapterResponse: LiveData<ChapterResponse?> = _bookChapterResponse
    private val _bookChapterError = MutableLiveData<Unit>()
    val bookChapterError: LiveData<Unit> = _bookChapterError
    var loadingStateLiveData = MutableLiveData<State>()

    fun retrieveBookChapters(bookId: String?) = viewModelScope.launch {
        loadingStateLiveData.value = State.LOADING
        when (val response = repository.retrieveBookChapters(bookId)) {
            is NetworkResponse.Failed -> {
                _bookChapterError.value = Unit
            }
            is NetworkResponse.Success -> {
                _bookChapterResponse.value = response.data
            }
        }
        loadingStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING,
        LOADING_FINISHED
    }
}