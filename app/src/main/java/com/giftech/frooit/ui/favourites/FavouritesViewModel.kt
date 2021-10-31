package com.giftech.frooit.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.frooit.core.domain.usecase.FruitUseCase

class FavouritesViewModel(fruitUseCase: FruitUseCase):ViewModel() {

    val favoriteFruit = fruitUseCase.getFavoriteFruit().asLiveData()

}