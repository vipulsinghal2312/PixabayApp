package com.anivale.pixabayapp.utils

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.utils.extension.getParentActivity
import com.bumptech.glide.Glide

@BindingAdapter("previewImageUrl")
fun setPreviewImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && url != null) {
        url.observe(parentActivity, Observer { value ->
            Glide.with(view.context).load(value).placeholder(R.drawable.image_placeholder)
                .into(view)
        })
    }
}

//@BindingAdapter("webImageUrl")
//fun setWebImageUrl(view: ImageView, url: MutableLiveData<String>?) {
//    val parentActivity: AppCompatActivity? = view.getParentActivity()
//    if (parentActivity != null && url != null) {
//        url.observe(parentActivity, Observer { value ->
//            Glide.with(view.context).load(value).placeholder(R.drawable.image_placeholder)
//                .into(view)
//        })
//    }
//}
