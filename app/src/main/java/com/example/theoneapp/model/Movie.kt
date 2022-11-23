package com.example.theoneapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie (
    @SerializedName("_id") val _id: String,
    @SerializedName("name") val name: String,
    @SerializedName("runtimeInMinutes") val runtimeInMinutes: Int,
    @SerializedName("budgetInMillions") val budgetInMillions: Int,
    @SerializedName("boxOfficeRevenueInMillions") val boxOfficeRevenueInMillions: Float,
    @SerializedName("academyAwardNominations") val academyAwardNominations: Int,
    @SerializedName("academyAwardWins") val academyAwardWins: Int,
    @SerializedName("rottenTomatoesScore") val rottenTomatoesScore: Float,
): Parcelable
