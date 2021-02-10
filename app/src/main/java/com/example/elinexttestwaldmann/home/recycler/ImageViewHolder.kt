package com.example.elinexttestwaldmann.home.recycler
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.elinexttestwaldmann.Constants.Companion.URL
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ItemImageBinding
import com.example.elinexttestwaldmann.model.ImageModel

class ImageViewHolder(binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    val image = binding.ivImage
    val error = binding.tvError
    val progressBar = binding.progressBar
    val context = binding.root.context

    fun bind(item: ImageModel.Image) {
            val request = ImageRequest.Builder(context)
                .data(URL)
                .target(image)
                .size(
                    context.resources.getDimensionPixelSize(R.dimen.cell_dimension),
                    context.resources.getDimensionPixelSize(R.dimen.cell_dimension))
                .transformations(RoundedCornersTransformation(context.resources.getDimension(R.dimen.cell_corner_radius)))
                .memoryCacheKey(item.toString())
                .listener(
                    onStart = {
                        progressBar.visibility = VISIBLE
                    },
                    onSuccess = { request, metadata ->
                        progressBar.visibility = View.GONE
                    },
                    onError = { request, throwable ->
                        progressBar.visibility = View.GONE
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
            return ImageViewHolder(binding)
        }
    }
}