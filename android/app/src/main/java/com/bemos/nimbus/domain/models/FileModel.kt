package com.bemos.nimbus.domain.models

import com.google.gson.annotations.SerializedName

data class FileModel(
    val name: String,
    @SerializedName("size_mb")
    val sizeMb: Float
)