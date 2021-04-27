package com.example.sentiatest.ui.home

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sentiatest.R
import com.example.sentiatest.data.Data

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     datas: List<Data>?) {
    val adapter = recyclerView.adapter as DataAdapter
    adapter.submitList(datas)
}

@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("agentImageUrl")
fun bindAgentImageView(imageView: ImageView, agentImageUrl: String?) {
    agentImageUrl?.let {
        val imageUri = agentImageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}