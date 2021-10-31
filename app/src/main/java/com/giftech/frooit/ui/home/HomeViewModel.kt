package com.giftech.frooit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.frooit.core.domain.usecase.FruitUseCase

class HomeViewModel(fruitUseCase: FruitUseCase) : ViewModel() {
    val fruit = fruitUseCase.getListFruit().asLiveData()
}