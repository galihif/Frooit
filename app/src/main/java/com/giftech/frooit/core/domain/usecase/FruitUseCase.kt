package com.giftech.frooit.core.domain.usecase

import androidx.lifecycle.LiveData
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit

interface FruitUseCase {

    fun getListFruit(): LiveData<Resource<List<Fruit>>>

    fun getFavoriteFruit(): LiveData<List<Fruit>>

    fun setFavoriteFruit(fruit: Fruit, state:Boolean)

}