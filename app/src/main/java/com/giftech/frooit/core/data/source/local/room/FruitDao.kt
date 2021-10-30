package com.giftech.frooit.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.giftech.frooit.core.data.source.local.entity.FruitEntity

@Dao
interface FruitDao {

    @Query("SELECT * FROM fruit")
    fun getAllFruit(): LiveData<List<FruitEntity>>

    @Query("SELECT * FROM fruit where isFavorite = 1")
    fun getFavoriteFruit(): LiveData<List<FruitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFruit(fruit: List<FruitEntity>)

    @Update
    fun updateFavoriteFruit(fruit: FruitEntity)
}
