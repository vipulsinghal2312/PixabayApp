package com.anivale.pixabayapp.injection.component

import com.anivale.pixabayapp.injection.module.NetworkModule
import com.anivale.pixabayapp.ui.imagedetails.ImageDetailsViewModel
import com.anivale.pixabayapp.ui.imagegallery.ImageListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(imageListViewModel: ImageListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}