package com.giftech.frooit.ui.favourites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.frooit.databinding.ActivityFavouritesBinding

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favourites"
    }
}