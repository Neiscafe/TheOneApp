package com.example.theoneapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(
    @SerializedName("docs") val characters: List<Character>
) : Serializable

