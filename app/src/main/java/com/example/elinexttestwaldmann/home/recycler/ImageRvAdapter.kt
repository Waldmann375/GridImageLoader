package com.example.elinexttestwaldmann.home.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.elinexttestwaldmann.model.ImageModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ImageRvAdapter @Inject constructor() : ListAdapter<ImageModel, ImageViewHolder>(
    IMAGE_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position) as ImageModel.Image)
    }

    companion object {
        val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return newItem::class == oldItem::class
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    }
}