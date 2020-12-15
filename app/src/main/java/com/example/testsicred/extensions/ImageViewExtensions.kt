package com.example.testsicred.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.testsicred.R

fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.error_image_generic)
            .into(this)
    } else {
        this.setImageResource(R.drawable.error_image_generic)
    }
}
