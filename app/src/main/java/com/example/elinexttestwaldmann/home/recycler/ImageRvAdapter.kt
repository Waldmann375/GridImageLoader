package com.example.elinexttestwaldmann.home.recycler

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.elinexttestwaldmann.model.ImageModel

class ImageRvAdapter(val list: MutableList<ImageModel>) : ListAdapter<ImageModel, ImageViewHolder>(
    IMAGE_COMPARATOR
) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is ImageModel.Image -> TYPE_IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list, position, getItem(position) as ImageModel.Image)
    }

    companion object {
        val TYPE_IMAGE = 1

        val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                //return newItem::class == oldItem::class
                return (newItem as ImageModel.Image).number == (oldItem as ImageModel.Image).number
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return (newItem as ImageModel.Image).number == (oldItem as ImageModel.Image).number
            }
        }
    }
}