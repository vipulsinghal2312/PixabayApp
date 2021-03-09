package com.anivale.pixabayapp.ui.imagedetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.anivale.pixabayapp.R
import com.anivale.pixabayapp.base.BaseActivity

class ImageDetailsActivity : BaseActivity() {
    
//    private lateinit var binding: ActivityImageDetailsBinding
//    private val viewModel: ImageDetailsViewModel by viewModels()
//    {
//        ImageDetailsViewModelFactory(
//            this,
//            intent.extras
//        )
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_details)
//        binding.viewModel = viewModel
//        viewModel.imageUrl.observe(this, Observer { imageUrl ->
//            if (!TextUtils.isEmpty(imageUrl)) {
//                Glide.with(this).load(imageUrl)
//                    .into(binding.imageView)
//            }
//        })
        setActionBar();
        addFragment(
            supportFragmentManager,
            ImageDetailsFragment.newInstance(),
            R.id.fl_fragment_container
        )
    }



    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_button)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.icon_ar_view) {

        }
        return super.onOptionsItemSelected(item)
    }
}
