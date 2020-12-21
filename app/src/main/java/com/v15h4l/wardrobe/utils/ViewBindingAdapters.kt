package com.v15h4l.wardrobe.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter(value = ["imageUri"], requireAll = false)
fun imageUri(imageView: ImageView, imageUri: String?) {
    Glide.with(imageView)
        .load(Uri.parse(imageUri))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}