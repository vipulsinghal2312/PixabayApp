package com.anivale.pixabayapp.ui.imagegallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.databinding.ActivityMainBinding
import com.anivale.pixabayapp.model.Image
import com.anivale.pixabayapp.ui.imagedetails.ImageDetailsActivity
import com.anivale.pixabayapp.utils.KEY_IMAGE__URL
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class ImageGalleryActivity : AppCompatActivity(), ImageListAdapter.ImageClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageListViewModel
    private var errorSnackbar: Snackbar? = null
    private lateinit var imageListAdapter: ImageListAdapter
    private var isScreenRotated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isScreenRotated = savedInstanceState?.getBoolean("is_rotated") ?: false
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

        viewModel = ViewModelProvider(this).get(ImageListViewModel::class.java)
        binding.viewModel = viewModel
        imageListAdapter = ImageListAdapter(this, viewModel.allImagesList)
        binding.recyclerView.adapter = imageListAdapter
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.latestPageImagesList.observe(this, Observer { latestPageImagesList ->
//            Log.d("imagelist", " -  ${imageList[0].id}")
            if (!isScreenRotated) {
                imageListAdapter.updateImageList(latestPageImagesList)
            } else {
                isScreenRotated = false
            }
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    val visibleItemsCount: Int = layoutManager?.childCount ?: 2
                    val totalItemsCount: Int =
                        layoutManager?.itemCount ?: viewModel.imagesCountPerPage
                    val firstVisibleItemPosition = when (layoutManager) {
                        is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                        else -> 0
                    }
                    if (!viewModel.isLoading && !viewModel.isLastPage &&
                        (visibleItemsCount + firstVisibleItemPosition) >= totalItemsCount &&
                        firstVisibleItemPosition >= 0
                    ) {
                        viewModel.pageCount++
                        loadImages()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
//        loadImages()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry) { loadImages() }
        errorSnackbar?.show()
    }

    private fun loadImages() {
        Log.d("imagelist_pagecount", viewModel.pageCount.toString())
        viewModel.loadImages(
            viewModel.searchString,
            viewModel.pageCount,
            viewModel.imagesCountPerPage
        )
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onImageClicked(image: Image) {
        val intent = Intent(this, ImageDetailsActivity::class.java)
        intent.putExtra(KEY_IMAGE__URL, image.webFormatUrl)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("is_rotated", true)
        super.onSaveInstanceState(outState)
    }
}
