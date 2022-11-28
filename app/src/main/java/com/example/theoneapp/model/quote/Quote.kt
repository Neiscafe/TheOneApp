package com.example.theoneapp.model.quote

import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("dialog") val dialog: String,
    @SerializedName("movie") val movieId: String
)