package com.example.testsicred.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["loadImage"])
fun ImageView.bindingLoadImage(url: String?) = loadImage(url)
