package com.anivale.pixabayapp.ui.imagedetails

import androidx.lifecycle.SavedStateHandle
import com.anivale.pixabayapp.base.BaseViewModel
import com.anivale.pixabayapp.utils.KEY_IMAGE__URL

class ImageDetailsViewModel(private val savedStateHandle: SavedStateHandle) : BaseViewModel() {

    val imageUrl = savedStateHandle.getLiveData<String>(KEY_IMAGE__URL)
}