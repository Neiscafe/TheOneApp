package com.example.theoneapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.theoneapp.model.*
import com.example.theoneapp.retrofit.Api

class RepositoryImpl(val api: Api) : Repository {

    override suspend fun retrieveBooks(): NetworkResponse<BookResponse> {
        return try {
            val response = api.retrieveBooks()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                Log.i(TAG, "retrieveBooks: response falhou")
                NetworkResponse.Failed(Exception())
            }
        } catch (e: Exception) {
            Log.i(TAG, "retrieveBooks: response nao foi")
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun retrieveMovie(): NetworkResponse<MovieResponse> {
        return try {
            val response = api.retrieveMovie()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                NetworkResponse.Failed(Exception())
            }
        } catch (e: Exception) {
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

    override suspend fun retrieveCharacters(): NetworkResponse<CharacterResponse> {
        return try {
            val response = api.retrieveCharacters()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body()!!)
            } else {
                NetworkResponse.Failed(Exception())
            }
        } catch (e: Exception) {
            NetworkResponse.Failed(ApiError.GenericException())
        }
    }

//    override suspend fun retrieveCharactersSearch(query: String): NetworkResponse<CharacterResponse> {
//        return try {
//            val response = api.retrieveCharactersSearch(query)
//            if (response.isSuccessful) {
//                NetworkResponse.Success(response.body()!!)
//            } else {
//                NetworkResponse.Failed(Exception())
//            }
//        } catch (e: Exception) {
//            NetworkResponse.Failed(ApiError.GenericException())
//        }
//    }
}

interface Repository {
    suspend fun retrieveBooks(): NetworkResponse<BookResponse>
    suspend fun retrieveMovie(): NetworkResponse<MovieResponse>
    suspend fun retrieveCharacters(): NetworkResponse<CharacterResponse>
//    suspend fun retrieveCharactersSearch(query: String): NetworkResponse<CharacterResponse>
}