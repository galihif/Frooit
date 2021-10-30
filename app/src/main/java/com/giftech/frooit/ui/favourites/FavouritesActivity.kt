package com.giftech.frooit.ui.favourites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.frooit.core.ui.ListFruitAdapter
import com.giftech.frooit.core.ui.ViewModelFactory
import com.giftech.frooit.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[FavouritesViewModel::class.java]
        val adapter = ListFruitAdapter()

        viewModel.getFavoriteFruit().observe(this, {res ->
            adapter.setList(res)
        })

        with(binding.rvFruit){
            layoutManager = LinearLayoutManager(this@FavouritesActivity)
            this.adapter = adapter
        }

        supportActionBar?.title = "Favourites"
    }
}