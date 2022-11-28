package com.example.theoneapp.model.character

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    @SerializedName("_id") val _id: String,
    @SerializedName("birth") val birth: String,
    @SerializedName("death") val death: String,
    @SerializedName("hair") val hair: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("height") val height: String,
    @SerializedName("realm") val realm: String,
    @SerializedName("spouse") val spouse: String,
    @SerializedName("name") val name: String,
    @SerializedName("race") val race: String,
    @SerializedName("wikiUrl") val wikiUrl: String,
): Parcelable

