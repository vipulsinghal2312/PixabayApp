package com.anivale.pixabayapp.injection.module

import com.anivale.pixabayapp.BuildConfig
import com.anivale.pixabayapp.network.PixabayImagesApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePixabayImagesApi(retrofit: Retrofit): PixabayImagesApi {
        return retrofit.create(PixabayImagesApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}