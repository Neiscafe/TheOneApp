package com.example.theoneapp.model

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("docs") val docs: List<Movie>
)