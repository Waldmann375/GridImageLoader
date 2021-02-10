package com.example.elinexttestwaldmann.home

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.example.elinexttestwaldmann.Constants
import com.example.elinexttestwaldmann.Constants.Companion.URL
import com.example.elinexttestwaldmann.databinding.ActivityMainBinding
import com.example.elinexttestwaldmann.home.recycler.ImageRvAdapter
import com.example.elinexttestwaldmann.model.ImageModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adatper: ImageRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
        /*val request = ImageRequest.Builder(this)
            .data(Constants.URL)
            //.target(binding.ivRootImage)
            .listener(
                onStart = {
                    binding.progressBar.visibility = VISIBLE
                },
                onSuccess = { request, metadata ->
                    binding.progressBar.visibility = GONE
                }
            )
            .target(binding.ivRootImage)
            .build()
        this.imageLoader.enqueue(request)*/
        //binding.ivRootImage.load(URL)
    }

    private fun initRv() {
        val imageList: MutableList<ImageModel> = mutableListOf()
        for (i in 0..139){
            imageList.add(ImageModel.Image(i))
        }

        adatper = ImageRvAdapter(imageList)
        binding.rvImages.layoutManager = GridLayoutManager(
            this,
            10,
            GridLayoutManager.HORIZONTAL,
            false
        )
        binding.rvImages.adapter = adatper
        adatper.submitList(
            imageList
        )
    }
}