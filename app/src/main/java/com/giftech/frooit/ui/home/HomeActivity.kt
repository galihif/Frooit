package com.giftech.frooit.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.frooit.R
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.ui.ListFruitAdapter
import com.giftech.frooit.databinding.ActivityHomeBinding
import com.giftech.frooit.ui.detail.DetailActivity
import com.giftech.frooit.ui.favourites.FavouritesActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private val viewModel:HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListFruitAdapter()

        adapter.onItemClick = {selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_FRUIT, selectedData)
            startActivity(intent)
        }

        viewModel.fruit.observe(this,{ res ->
            when(res){
                is Resource.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                is Resource.Error -> Toast.makeText(this, res.message, Toast.LENGTH_LONG).show()
                is Resource.Success -> adapter.setList(res.data!!)
            }
        })

        with(binding.rvFruit){
            layoutManager = LinearLayoutManager(this@HomeActivity)
            this.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_favourite -> {
                val intent = Intent(this, FavouritesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}