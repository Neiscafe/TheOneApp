package com.example.theoneapp.model

import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("dialog") val dialog: String,
    @SerializedName("movie") val movieId: String
)