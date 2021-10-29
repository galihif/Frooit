package com.giftech.frooit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.response.FruitResponse

class HomeViewModel(private val fruitRepository: FruitRepository):ViewModel() {

    fun getListFruit():LiveData<List<FruitResponse>> = fruitRepository.getListFruit()

}