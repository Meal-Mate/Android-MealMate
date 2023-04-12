package com.example.mealmate.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


object GlideHelper {
    fun ImageView.loadImage(url: String?) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}