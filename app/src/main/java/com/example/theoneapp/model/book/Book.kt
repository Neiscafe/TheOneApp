package com.example.theoneapp.model.book

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Book(
    @SerializedName("_id") val _id: String,
    @SerializedName("name") val name: String
): Parcelable