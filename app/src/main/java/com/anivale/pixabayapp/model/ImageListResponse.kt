package com.anivale.pixabayapp.model

import com.google.gson.annotations.SerializedName

data class ImageListResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("hits")
    val images: MutableList<Image>
)