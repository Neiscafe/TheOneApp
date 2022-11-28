package com.example.theoneapp.model.chapter

import com.google.gson.annotations.SerializedName

data class ChapterResponse(
    @SerializedName("docs") val docs: List<Chapter>,
)
