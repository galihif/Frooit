package com.giftech.frooit.core.domain.repository

import androidx.lifecycle.LiveData
import com.giftech.frooit.core.data.Resource
import com.giftech.frooit.core.domain.model.Fruit

interface IFruitRepository {

    fun getListFruit(): LiveData<Resource<List<Fruit>>>

    fun getFavoriteFruit():LiveData<List<Fruit>>

    fun setFavoriteFruit(fruit:Fruit, state:Boolean)

}