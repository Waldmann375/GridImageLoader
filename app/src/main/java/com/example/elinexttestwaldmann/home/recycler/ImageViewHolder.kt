package com.example.elinexttestwaldmann.home.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.elinexttestwaldmann.Constants.Companion.DIMENSIONS
import com.example.elinexttestwaldmann.Constants.Companion.URL
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ItemImageBinding
import com.example.elinexttestwaldmann.model.ImageModel

class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    val image = binding.ivImage
    val error = binding.tvError
    val context = binding.root.context

    fun bind(item: ImageModel.Image) {

        if (false) {
            binding.ivImage.load(URL) {
                memoryCacheKey(item.toString())
                //placeholderMemoryCacheKey(item.number.toString())
                size(DIMENSIONS, DIMENSIONS)
                transformations(RoundedCornersTransformation(7f))
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error)
            }
        } else {
            val request = ImageRequest.Builder(context)
                .data(URL)
                .target(binding.ivImage)
                .size(DIMENSIONS, DIMENSIONS) // костыль :)
                .transformations(RoundedCornersTransformation(7f))
                .memoryCacheKey(item.toString())
                .listener(
                    onStart = {
                        binding.progressBar.visibility = VISIBLE
                    },
                    onSuccess = { request, metadata ->
                        binding.progressBar.visibility = View.GONE
                    },
                    onError = { request, throwable ->
                        binding.progressBar.visibility = View.GONE
                        error.visibility = VISIBLE
                    }
                )
                .build()
            context.imageLoader.enqueue(request)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemImageBinding.inflate(layoutInflater)
            return ImageViewHolder(binding)
        }
    }
}