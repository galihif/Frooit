package com.giftech.frooit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit

class HomeViewModel(private val fruitRepository: FruitRepository):ViewModel() {

    fun getListFruit():LiveData<Resource<List<Fruit>>> = fruitRepository.getListFruit()

}