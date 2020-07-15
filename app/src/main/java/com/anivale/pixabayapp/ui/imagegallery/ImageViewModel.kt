package com.anivale.pixabayapp.ui.imagegallery

import androidx.lifecycle.MutableLiveData
import com.anivale.pixabayapp.base.BaseViewModel
import com.anivale.pixabayapp.model.Image

class ImageViewModel : BaseViewModel() {
    private val previewImageUrl = MutableLiveData<String>()
    private val webFormatUrl = MutableLiveData<String>()

    fun bind(image: Image) {
        previewImageUrl.value = image.previewImageUrl
        webFormatUrl.value = image.webFormatUrl
    }

    fun getPreviewImageUrl(): MutableLiveData<String> {
        return previewImageUrl
    }

    fun getWebFormatUrl(): MutableLiveData<String> {
        return webFormatUrl
    }

}