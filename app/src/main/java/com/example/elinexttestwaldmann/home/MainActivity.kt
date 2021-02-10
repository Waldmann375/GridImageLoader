package com.example.elinexttestwaldmann.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import coil.imageLoader
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ActivityMainBinding
import com.example.elinexttestwaldmann.home.recycler.ImageRvAdapter
import com.example.elinexttestwaldmann.model.ImageModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adatper: ImageRvAdapter
    var imageList: MutableList<ImageModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        initRv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.action_reload ->{
                reloadList()
            }
            R.id.action_add ->{
                addImage()
            }
        }
            return super.onOptionsItemSelected(item)
    }

    private fun getNewList():MutableList<ImageModel>{
        val list = mutableListOf<ImageModel>()
        for (i in 0..136) {
            list.add(ImageModel.Image(i))
        }
        imageList = list
        return list
    }

    private fun reloadList(){
        //imageList = mutableListOf()
        binding.rvImages.adapter = null
        adatper = ImageRvAdapter()
        binding.rvImages.adapter = adatper
        adatper.submitList(getNewList())
        /*Toast.makeText(this, "reload fun operates", Toast.LENGTH_SHORT).show()
        imageList.clear()
        adatper.submitList(updateAndGetList())*/
        //adatper.notifyDataSetChanged()
        /*populateList()
        adatper.submitList(imageList)
        adatper.notifyDataSetChanged()*/
    }

    private fun addImage() {
        Toast.makeText(this, "plus fun operates", Toast.LENGTH_SHORT).show()
        imageList.add(ImageModel.Image(imageList.size))
        adatper.submitList(imageList)
        adatper.notifyItemInserted(imageList.size)
    }

    private fun initRv() {
        adatper = ImageRvAdapter()
        binding.rvImages.layoutManager = GridLayoutManager(
            this,
            10,
            GridLayoutManager.HORIZONTAL,
            false
        )
        binding.rvImages.adapter = adatper
        adatper.submitList(
            getNewList()
        )
    }
}