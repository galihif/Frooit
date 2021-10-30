package com.giftech.frooit.core.data.source.local

import androidx.lifecycle.LiveData
import com.giftech.frooit.core.data.source.local.entity.FruitEntity
import com.giftech.frooit.core.data.source.local.room.FruitDao

class LocalDataSource private constructor(private val fruitDao: FruitDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(fruitDao: FruitDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(fruitDao)
            }
    }

    fun getAllFruit(): LiveData<List<FruitEntity>> = fruitDao.getAllFruit()

    fun getFavoriteFruit(): LiveData<List<FruitEntity>> = fruitDao.getFavoriteFruit()

    fun insertFruit(listFruit: List<FruitEntity>) = fruitDao.insertFruit(listFruit)

    fun setFavoriteFruit(fruit: FruitEntity, newState: Boolean) {
        fruit.isFavorite = newState
        fruitDao.updateFavoriteFruit(fruit)
    }
}