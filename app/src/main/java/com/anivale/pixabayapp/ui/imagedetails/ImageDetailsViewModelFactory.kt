package com.anivale.pixabayapp.ui.imagedetails

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class ImageDetailsViewModelFactory(owner: SavedStateRegistryOwner, defaultArgs: Bundle?) :
    AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ImageDetailsViewModel(handle) as T
    }
}