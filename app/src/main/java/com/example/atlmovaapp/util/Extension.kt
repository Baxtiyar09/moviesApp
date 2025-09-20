package com.example.atlmovaapp.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        val fullUrl = "https://image.tmdb.org/t/p/original" + it
        Glide.with(this.context)
            .load(fullUrl)
            .into(this)
    }
}

fun ImageView.loadImageYoutube(key: String?) {
    key?.let {
        val youtubeUrl = "https://img.youtube.com/vi/" + it + "/hqdefault.jpg"
        Glide.with(this.context)
            .load(youtubeUrl)
            .into(this)
    }
}

