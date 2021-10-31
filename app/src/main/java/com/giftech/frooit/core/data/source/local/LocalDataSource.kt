package com.giftech.frooit.core.data.source.local

import com.giftech.frooit.core.data.source.local.entity.FruitEntity
import com.giftech.frooit.core.data.source.local.room.FruitDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val fruitDao: FruitDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(fruitDao: FruitDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(fruitDao)
            }
    }

    fun getAllFruit(): Flow<List<FruitEntity>> = fruitDao.getAllFruit()

    fun getFavoriteFruit(): Flow<List<FruitEntity>> = fruitDao.getFavoriteFruit()

    suspend fun insertFruit(listFruit: List<FruitEntity>) = fruitDao.insertFruit(listFruit)

    fun setFavoriteFruit(fruit: FruitEntity, newState: Boolean) {
        fruit.isFavorite = newState
        fruitDao.updateFavoriteFruit(fruit)
    }
}