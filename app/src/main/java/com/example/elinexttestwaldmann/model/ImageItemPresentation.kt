package com.example.elinexttestwaldmann.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable


sealed class ImageModel{
    class Image(val number:Int, var drawable:Drawable? = null):ImageModel()
}