package com.example.theoneapp.retrofit

import com.example.theoneapp.model.BookResponse
import com.example.theoneapp.model.CharacterResponse
import com.example.theoneapp.model.MovieResponse
import com.example.theoneapp.model.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("book")
    suspend fun retrieveBooks(): Response<BookResponse>

    @GET("movie")
    suspend fun retrieveMovie(): Response<MovieResponse>

    @GET("character")
    suspend fun retrieveCharacters(): Response<CharacterResponse>

    @GET("character/{id}/quote")
    suspend fun retrieveCharacterQuote(
        @Path("id") characterId: String?
    ): Response<QuoteResponse>


}
