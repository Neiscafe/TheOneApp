package com.example.theoneapp.model

object ApiError {
    data class GenericException(override val message: String? = null): Exception()
}