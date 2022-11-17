package com.example.theoneapp.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("docs") val quoteData: List<Quote>
)
