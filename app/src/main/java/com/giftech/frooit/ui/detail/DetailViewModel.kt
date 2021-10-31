package com.giftech.frooit.ui.detail

import androidx.lifecycle.ViewModel
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.usecase.FruitUseCase

class DetailViewModel(private val fruitUseCase: FruitUseCase):ViewModel() {

    fun setFavoriteFruit(fruit: Fruit, state:Boolean) = fruitUseCase.setFavoriteFruit(fruit, state)

}