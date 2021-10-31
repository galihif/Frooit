package com.giftech.frooit.ui.favourites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.frooit.core.ui.ListFruitAdapter
import com.giftech.frooit.databinding.ActivityFavouritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFavouritesBinding
    private val viewModel:FavouritesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListFruitAdapter()

        viewModel.favoriteFruit.observe(this, { res ->
            adapter.setList(res)
        })

        with(binding.rvFruit){
            layoutManager = LinearLayoutManager(this@FavouritesActivity)
            this.adapter = adapter
        }

        supportActionBar?.title = "Favourites"
    }
}