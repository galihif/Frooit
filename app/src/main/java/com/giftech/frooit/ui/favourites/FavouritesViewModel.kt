package com.giftech.frooit.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.usecase.FruitUseCase

class FavouritesViewModel(private val fruitUseCase: FruitUseCase):ViewModel() {

    fun getFavoriteFruit():LiveData<List<Fruit>> = fruitUseCase.getFavoriteFruit().asLiveData()

}