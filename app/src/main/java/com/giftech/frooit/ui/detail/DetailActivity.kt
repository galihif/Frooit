package com.giftech.frooit.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.frooit.databinding.ActivityDetailBinding
import com.giftech.frooit.domain.model.Fruit

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent != null){
            val extras = intent.extras
            val fruit = extras?.getParcelable<Fruit>(EXTRA_FRUIT)

            populateView(fruit!!)
        }
    }

    private fun populateView(fruit: Fruit) {
        with(binding){
            tvName.text = fruit.name
            tvFamily.text = fruit.family
            tvGenus.text = fruit.genus

            supportActionBar?.title = fruit.name
        }
    }

    companion object{
        const val EXTRA_FRUIT = "EXTRA_FRUIT"
    }
}