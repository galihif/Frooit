package com.giftech.frooit.core.domain.usecase

import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.repository.IFruitRepository
import kotlinx.coroutines.flow.Flow

class FruitInteractor(private val repository: IFruitRepository): FruitUseCase {

    override fun getListFruit(): Flow<Resource<List<Fruit>>> = repository.getListFruit()

    override fun getFavoriteFruit(): Flow<List<Fruit>> = repository.getFavoriteFruit()

    override fun setFavoriteFruit(fruit: Fruit, state: Boolean) = repository.setFavoriteFruit(fruit,state)
}