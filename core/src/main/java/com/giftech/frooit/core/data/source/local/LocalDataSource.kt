package com.giftech.frooit.core.data.source.local

import com.giftech.frooit.core.data.source.local.entity.FruitEntity
import com.giftech.frooit.core.data.source.local.room.FruitDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val fruitDao: com.giftech.frooit.core.data.source.local.room.FruitDao) {

    companion object {
        private var instance: com.giftech.frooit.core.data.source.local.LocalDataSource? = null

        fun getInstance(fruitDao: com.giftech.frooit.core.data.source.local.room.FruitDao): com.giftech.frooit.core.data.source.local.LocalDataSource =
            com.giftech.frooit.core.data.source.local.LocalDataSource.Companion.instance ?: synchronized(this) {
                com.giftech.frooit.core.data.source.local.LocalDataSource.Companion.instance
                    ?: com.giftech.frooit.core.data.source.local.LocalDataSource(fruitDao)
            }
    }

    fun getAllFruit(): Flow<List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>> = fruitDao.getAllFruit()

    fun getFavoriteFruit(): Flow<List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>> = fruitDao.getFavoriteFruit()

    suspend fun insertFruit(listFruit: List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>) = fruitDao.insertFruit(listFruit)

    fun setFavoriteFruit(fruit: com.giftech.frooit.core.data.source.local.entity.FruitEntity, newState: Boolean) {
        fruit.isFavorite = newState
        fruitDao.updateFavoriteFruit(fruit)
    }
}