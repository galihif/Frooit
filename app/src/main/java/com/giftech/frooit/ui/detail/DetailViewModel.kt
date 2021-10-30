package com.giftech.frooit.ui.detail

import androidx.lifecycle.ViewModel
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.domain.model.Fruit

class DetailViewModel(private val fruitRepository: FruitRepository):ViewModel() {

    fun setFavoriteFruit(fruit: Fruit, state:Boolean) = fruitRepository.setFavoriteFruit(fruit, state)

}