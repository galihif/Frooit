package com.giftech.frooit.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.giftech.frooit.R
import com.giftech.frooit.core.utils.DummyData
import com.giftech.frooit.databinding.ActivityHomeBinding
import com.giftech.frooit.ui.favourites.FavouritesActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListFruitAdapter()
        adapter.setList(DummyData.generateListFruit())

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