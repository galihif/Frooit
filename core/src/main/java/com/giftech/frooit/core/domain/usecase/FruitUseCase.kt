package com.giftech.frooit.core.domain.usecase

import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit
import kotlinx.coroutines.flow.Flow

interface FruitUseCase {

    fun getListFruit(): Flow<com.giftech.frooit.core.data.Resource<List<Fruit>>>

    fun getFavoriteFruit(): Flow<List<Fruit>>

    fun setFavoriteFruit(fruit: Fruit, state:Boolean)

}