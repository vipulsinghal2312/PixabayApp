package com.anivale.pixabayapp.base

import androidx.lifecycle.ViewModel
import com.anivale.pixabayapp.injection.component.DaggerViewModelInjector
import com.anivale.pixabayapp.injection.component.ViewModelInjector
import com.anivale.pixabayapp.injection.module.NetworkModule
import com.anivale.pixabayapp.ui.imagegallery.ImageListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ImageListViewModel -> injector.inject(this)
        }
    }
}