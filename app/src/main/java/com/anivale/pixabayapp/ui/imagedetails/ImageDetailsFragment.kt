package com.anivale.pixabayapp.ui.imagedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.anivale.pixabayapp.databinding.ImageDetailsFragmentBinding

class ImageDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ImageDetailsFragment()
    }

    //        private lateinit var viewModel: ImageDetailsViewModel
    private val viewModel: ImageDetailsViewModel by activityViewModels()
    private lateinit var binding: ImageDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("flow_life", "oncreatview")
        binding = ImageDetailsFragmentBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
//        return inflater.inflate(R.layout.image_details_fragment, container, false)
    }

}
