package com.anivale.pixabayapp.ui.imagedetails

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.databinding.ActivityImageDetailsBinding
import com.anivale.pixabayapp.utils.KEY_IMAGE__URL
import com.bumptech.glide.Glide

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_details)
        init()
    }

    private fun init() {
        val bundle = intent.extras
        val url = bundle?.getString(KEY_IMAGE__URL)
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this).load(url).placeholder(R.drawable.image_placeholder)
                .into(binding.imageView)
        }
    }
}
