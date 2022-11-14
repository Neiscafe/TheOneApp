package com.example.theoneapp.retrofit

import com.example.theoneapp.model.BookResponse
import com.example.theoneapp.model.CharacterResponse
import com.example.theoneapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("book")
    suspend fun retrieveBooks(): Response<BookResponse>

    @GET("movie")
    suspend fun retrieveMovie(): Response<MovieResponse>

    @GET("character")
    suspend fun retrieveCharacters(): Response<CharacterResponse>


//    suspend fun retrieveCharactersSearch(query: String): Any

}
