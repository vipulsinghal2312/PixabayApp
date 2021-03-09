package com.anivale.pixabayapp.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseActivity : AppCompatActivity() {

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        val transaction =
            fragmentManager.beginTransaction()
//        transaction.setCustomAnimations(
//            android.R.anim.slide_in_left,
//            android.R.anim.slide_out_right
//        )
//        transaction.addToBackStack("")
        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(frameId, fragment)
        }
        transaction.commit()
    }
}