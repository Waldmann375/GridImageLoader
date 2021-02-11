package com.example.elinexttestwaldmann.home.recycler

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.elinexttestwaldmann.Constants.Companion.COLUMNS_COUNT
import com.example.elinexttestwaldmann.Constants.Companion.URL
import com.example.elinexttestwaldmann.ElinextTestApplication
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ItemImageBinding
import com.example.elinexttestwaldmann.model.ImageModel

class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    val image = binding.ivImage
    val error = binding.tvError
    val progressBar = binding.progressBar
    val context = binding.root.context

    fun bind(item: ImageModel.Image) {
        val request = ImageRequest.Builder(context)
            .data(URL)
            .target(image)
            .scale(Scale.FILL)
            .transformations(RoundedCornersTransformation(context.resources.getDimension(R.dimen.cell_corner_radius)))
            .memoryCacheKey(item.toString())
            .listener(
                onStart = {
                    progressBar.visibility = VISIBLE
                    error.visibility = GONE
                },
                onSuccess = { request, metadata ->
                    progressBar.visibility = GONE
                    error.visibility = GONE
                },
                onError = { request, throwable ->
                    progressBar.visibility = GONE
                    error.visibility = VISIBLE
                }
            )
            .build()
        context.imageLoader.enqueue(request)
    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemImageBinding.inflate(layoutInflater)
            val params = FrameLayout.LayoutParams(getCellWidth(), MATCH_PARENT)
            binding.root.layoutParams = params
            return ImageViewHolder(binding)
        }

        private fun getCellWidth():Int {
            val width: Int = Resources.getSystem().displayMetrics.widthPixels
            val spaces: Int = ElinextTestApplication.applicationContext.resources.getDimensionPixelSize(R.dimen.cell_spacing)*15
            return (width-spaces)/COLUMNS_COUNT
        }
    }
}