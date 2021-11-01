package com.giftech.frooit.core.data.source.local.room

import androidx.room.*
import com.giftech.frooit.core.data.source.local.entity.FruitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Query("SELECT * FROM fruit")
    fun getAllFruit(): Flow<List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>>

    @Query("SELECT * FROM fruit where isFavorite = 1")
    fun getFavoriteFruit(): Flow<List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFruit(fruit: List<com.giftech.frooit.core.data.source.local.entity.FruitEntity>)

    @Update
    fun updateFavoriteFruit(fruit: com.giftech.frooit.core.data.source.local.entity.FruitEntity)
}
