package com.example.theoneapp.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("docs") val docs: List<Book>,
    @SerializedName("total") val total: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int
)