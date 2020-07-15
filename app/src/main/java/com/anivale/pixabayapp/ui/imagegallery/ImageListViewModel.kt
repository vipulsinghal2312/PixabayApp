package com.anivale.pixabayapp.ui.imagegallery

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.base.BaseViewModel
import com.anivale.pixabayapp.model.Image
import com.anivale.pixabayapp.model.ImageListResponse
import com.anivale.pixabayapp.network.PixabayImagesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageListViewModel : BaseViewModel() {
    @Inject
    lateinit var imagesApi: PixabayImagesApi
    private lateinit var subscription: Disposable
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    var imageList: MutableLiveData<MutableList<Image>> = MutableLiveData()
    val searchString: String = ""
    var pageCount: Int = 1
    val imagesCountPerPage: Int = 40
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    fun loadImages(searchString: String, page: Int, perPage: Int) {
        subscription = imagesApi.getImages(searchString, page, perPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveImageListStart() }
            .subscribe(
                { result -> onRetrieveImageListSuccess(result) },
                { throwable -> onRetrieveImageListError(throwable) })
    }

    private fun onRetrieveImageListStart() {
        isLoading = true
        errorMessage.value = null
    }

    private fun onRetrieveImageListSuccess(imageListResponse: ImageListResponse) {
        if (pageCount == 1)
            imageList.value = imageListResponse.images
        else
            imageList.value?.addAll(imageListResponse.images)
        isLoading = false
        if (Math.ceil(imageListResponse.total / imagesCountPerPage.toDouble()).toInt() == pageCount) {
            isLastPage = true
        }
    }

    private fun onRetrieveImageListError(throwable: Throwable) {
        Log.d("images", "error - ${throwable.message}")
        isLoading = false
        errorMessage.value = R.string.error_msg
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}