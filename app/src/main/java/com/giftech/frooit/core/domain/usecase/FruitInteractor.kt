package com.giftech.frooit.core.domain.usecase

import androidx.lifecycle.LiveData
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.repository.IFruitRepository

class FruitInteractor(private val repository: IFruitRepository): FruitUseCase {

    override fun getListFruit(): LiveData<Resource<List<Fruit>>> = repository.getListFruit()

    override fun getFavoriteFruit(): LiveData<List<Fruit>> = repository.getFavoriteFruit()

    override fun setFavoriteFruit(fruit: Fruit, state: Boolean) = repository.setFavoriteFruit(fruit,state)
}