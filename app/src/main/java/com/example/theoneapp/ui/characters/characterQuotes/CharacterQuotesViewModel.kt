package com.example.theoneapp.ui.characters.characterQuotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theoneapp.model.NetworkResponse
import com.example.theoneapp.model.Quote
import com.example.theoneapp.model.QuoteResponse
import com.example.theoneapp.repository.Repository
import kotlinx.coroutines.launch

class CharacterQuotesViewModel(val repository: Repository) : ViewModel() {

    private val _characterQuotesResponse = MutableLiveData<QuoteResponse?>()
    val characterQuotesResponse: LiveData<QuoteResponse?> = _characterQuotesResponse
    private val _characterQuotesError = MutableLiveData<Unit>()
    val characterQuotesError: LiveData<Unit> = _characterQuotesError
    var loadingStateLiveData = MutableLiveData<State>()

    fun retrieveCharacterQuotes(characterId: String?) = viewModelScope.launch {
        loadingStateLiveData.value = State.LOADING
        when (val response = repository.retrieveCharacterQuotes(characterId)) {
            is NetworkResponse.Failed -> {
                _characterQuotesError.value = Unit
            }
            is NetworkResponse.Success -> {
                _characterQuotesResponse.value = response.data
            }
        }
        loadingStateLiveData.value = State.LOADING_FINISHED
    }

    enum class State {
        LOADING,
        LOADING_FINISHED
    }
}