package com.example.elinexttestwaldmann.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.elinexttestwaldmann.Constants.Companion.ROWS_COUNT
import com.example.elinexttestwaldmann.R
import com.example.elinexttestwaldmann.databinding.ActivityMainBinding
import com.example.elinexttestwaldmann.home.recycler.ImageRvAdapter
import com.example.elinexttestwaldmann.home.recycler.ItemSpacingDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ImagesViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var adapter: ImageRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initRv()

        lifecycleScope.launchWhenStarted {
            viewModel.imageFlow.collect {
                when (it) {
                    is ImageState.ImageList.IncreasedImageList -> {
                        adapter.submitList(it.list)
                        adapter.notifyItemInserted(it.list.size)
                        binding.rvImages.scrollToPosition(adapter.itemCount - 1)
                    }
                    is ImageState.ImageList.NewImageList -> {
                        adapter.submitList(it.list)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_reload -> {
                reloadList()
            }
            R.id.action_add -> {
                addImage()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun reloadList() {
        viewModel.reloadImages()
    }

    private fun addImage() {
        viewModel.addImage()
    }

    //RV setup
    private fun initRv() {
        binding.rvImages.layoutManager = GridLayoutManager(
            this,
            ROWS_COUNT,
            GridLayoutManager.HORIZONTAL,
            false
        )
        binding.rvImages.addItemDecoration(
            ItemSpacingDecorator(
                resources.getDimensionPixelSize(R.dimen.cell_spacing)
            )
        )
        binding.rvImages.adapter = adapter
    }
}