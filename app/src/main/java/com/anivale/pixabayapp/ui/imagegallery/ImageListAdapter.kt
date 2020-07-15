package com.anivale.pixabayapp.ui.imagegallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.databinding.ItemGalleryImageBinding
import com.anivale.pixabayapp.model.Image

class ImageListAdapter(private val listener: ImageClickListener) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    private lateinit var imageList: List<Image>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGalleryImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery_image,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
        holder.binding.root.setOnClickListener { listener.onImageClicked(imageList[position]) }
    }

    override fun getItemCount(): Int {
        return if (::imageList.isInitialized) imageList.size else 0
    }

    fun updateImageList(imageList: List<Image>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemGalleryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ImageViewModel()

        fun bind(image: Image) {
            viewModel.bind(image)
            binding.viewModel = viewModel
        }
    }

    interface ImageClickListener {
        fun onImageClicked(image: Image)
    }
}