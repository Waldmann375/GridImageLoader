package com.example.elinexttestwaldmann.home.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.elinexttestwaldmann.Constants.Companion.URL
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ItemImageBinding
import com.example.elinexttestwaldmann.databinding.ItemImageSimpleBinding
import com.example.elinexttestwaldmann.model.ImageModel

class ImageViewHolder(val binding: ItemImageSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
    //val image = binding.ivImage
    //val error = binding.tvError
    val context = binding.root.context

    fun bind(list: MutableList<ImageModel>, position: Int, item: ImageModel.Image) {
        //val currentItem: ImageModel.Image = list[position] as ImageModel.Image

        if (true) {
            binding.ivImageSimple.load(URL){
                memoryCacheKey(item.number.toString())
                transformations(RoundedCornersTransformation(1f))
                placeholder(R.drawable.ic_progress)
                error(R.drawable.ic_error)
            }
        } else {
            /*val request = ImageRequest.Builder(context)
                .data(URL)
                .target(binding.ivImage)
                .transformations(RoundedCornersTransformation(7f))
                .memoryCacheKey(item.number.toString())
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
            context.imageLoader.enqueue(request)*/
        }
    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemImageSimpleBinding.inflate(layoutInflater)
            return ImageViewHolder(binding)
        }
    }
}