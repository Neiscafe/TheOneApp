package com.example.theoneapp.model

sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T): NetworkResponse<T>()
    data class Failed(val error: Exception): NetworkResponse<Nothing>()
}