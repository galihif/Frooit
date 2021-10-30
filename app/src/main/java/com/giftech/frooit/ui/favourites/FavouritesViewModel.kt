package com.giftech.frooit.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.domain.model.Fruit

class FavouritesViewModel(private val fruitRepository: FruitRepository):ViewModel() {

    fun getFavoriteFruit():LiveData<List<Fruit>> = fruitRepository.getFavoriteFruit()

}