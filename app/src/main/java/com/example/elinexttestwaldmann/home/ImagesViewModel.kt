package com.example.elinexttestwaldmann.home

import androidx.lifecycle.ViewModel
import com.example.elinexttestwaldmann.Constants
import com.example.elinexttestwaldmann.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class ImageState {
    sealed class ImageList(val list: MutableList<ImageModel>) : ImageState() {
        class NewImageList(list: MutableList<ImageModel>) : ImageList(list)
        class IncreasedImageList(list: MutableList<ImageModel>) : ImageList(list)
    }
    object InitialValue : ImageState()
}

@HiltViewModel
class ImagesViewModel @Inject constructor() : ViewModel() {

    private val _imageFlow: MutableStateFlow<ImageState> = MutableStateFlow(ImageState.InitialValue)
    val imageFlow: StateFlow<ImageState> = _imageFlow

    init {
        _imageFlow.value = ImageState.ImageList.NewImageList(getNewList())
    }

    private fun getNewList(): MutableList<ImageModel> {
        val list = mutableListOf<ImageModel>()
        for (i in Constants.LIST_SIZE_RANGE) {
            list.add(ImageModel.Image())
        }
        return list
    }

    fun addImage() {
        if (_imageFlow.value is ImageState.ImageList) {
            val list = (_imageFlow.value as ImageState.ImageList).list
            list.add(ImageModel.Image())
            _imageFlow.value = ImageState.ImageList.IncreasedImageList(list)
        }
    }

    fun reloadImages() {
        _imageFlow.value = ImageState.ImageList.NewImageList(getNewList())
    }

}