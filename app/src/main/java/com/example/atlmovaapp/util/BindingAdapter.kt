package com.example.atlmovaapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.atlmovaapp.R


@BindingAdapter("load_image_resource")
fun loadImage(imageView: ImageView, url: String?) {
    val fullUrl = "https://image.tmdb.org/t/p/w500${url}"
    Glide
        .with(imageView.context)
        .load(fullUrl)
        .placeholder(android.R.drawable.ic_menu_gallery)
        .into(imageView)
}

@BindingAdapter("load_url_youtube")
fun loadImageYoutube(imageView: ImageView, key: String?) {
    if (key.isNullOrEmpty()) {
        imageView.setImageResource(android.R.drawable.ic_menu_gallery)
    } else {
        imageView.loadImageYoutube(key)
    }
}