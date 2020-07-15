package com.anivale.pixabayapp.network

import com.anivale.pixabayapp.BuildConfig
import com.anivale.pixabayapp.model.ImageListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayImagesApi {

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}")
    fun getImages(@Query("q") query: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): Observable<ImageListResponse>
}