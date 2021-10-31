package com.giftech.frooit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.usecase.FruitUseCase

class HomeViewModel(private val fruitUseCase: FruitUseCase):ViewModel() {

    fun getListFruit():LiveData<Resource<List<Fruit>>> = fruitUseCase.getListFruit().asLiveData()

}