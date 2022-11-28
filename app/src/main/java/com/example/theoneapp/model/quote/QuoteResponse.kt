package com.example.theoneapp.model.quote

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("docs") val quoteData: List<Quote>
)
