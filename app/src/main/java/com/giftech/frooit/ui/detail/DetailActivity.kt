package com.giftech.frooit.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

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
            supportActionBar?.title = fruit.name
            tvName.text = fruit.name
            tvFamily.text = fruit.family
            tvGenus.text = fruit.genus

            var isFavorite = fruit.isFavorite
            setButtonFavorite(isFavorite)
            btnFav.setOnClickListener {
                isFavorite = !isFavorite
               viewModel.setFavoriteFruit(fruit, isFavorite)
               setButtonFavorite(isFavorite)
            }

        }
    }

    private fun setButtonFavorite(isFavorite:Boolean){
        if(isFavorite){
            binding.btnFav.text = "Remove From Favorites"
        } else{
            binding.btnFav.text = "Add To Favorites"
        }
    }

    companion object{
        const val EXTRA_FRUIT = "EXTRA_FRUIT"
    }
}