package com.giftech.frooit.favourites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.frooit.core.ui.ListFruitAdapter
import com.giftech.frooit.databinding.ActivityFavouritesBinding
import com.giftech.frooit.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavouritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favouritesModule)

        val adapter = ListFruitAdapter()

        adapter.onItemClick = {selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FRUIT, selectedData)
            startActivity(intent)
        }

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