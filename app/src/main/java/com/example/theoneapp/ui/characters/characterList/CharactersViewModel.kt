package com.example.theoneapp.ui.characters.characterList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theoneapp.model.CharacterResponse
import com.example.theoneapp.model.NetworkResponse
import com.example.theoneapp.repository.Repository
import kotlinx.coroutines.launch

class CharactersViewModel(val repository: Repository) : ViewModel() {

    private val _charactersResponse = MutableLiveData<CharacterResponse?>()
    val charactersResponse: LiveData<CharacterResponse?> = _charactersResponse
    private val _characterError = MutableLiveData<Unit>()
    val characterError: LiveData<Unit> = _characterError
    var loadingStateLiveData = MutableLiveData<State>()

    fun retrieveCharacters() = viewModelScope.launch {
        loadingStateLiveData.value = State.LOADING
        when(val response = repository.retrieveCharacters()){
            is NetworkResponse.Failed -> {
                _characterError.value = Unit
            }
            is NetworkResponse.Success -> {
                _charactersResponse.value = response.data
            }
        }
        loadingStateLiveData.value = State.LOADING_FINISHED
    }

//    fun characterSearch(p0: String?) {
//        loadingStateLiveData.value = State.LOADING
//        when(val response = repository.retrieveCharactersSearch()){
//            is NetworkResponse.Failed -> {
//                _characterError.value = Unit
//            }
//            is NetworkResponse.Success -> {
//                _charactersResponse.value = response.data
//            }
//        }
//        loadingStateLiveData.value = State.LOADING_FINISHED
//    }

    enum class State {
        LOADING,
        LOADING_FINISHED
    }
}