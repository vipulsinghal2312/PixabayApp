package com.anivale.pixabayapp.model

import com.google.gson.annotations.SerializedName

data class Image(
    val id: Int,

    @SerializedName("previewURL")
    val previewImageUrl: String,

    @SerializedName("webformatURL")
    val webFormatUrl: String
)