package com.giftech.frooit.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giftech.frooit.core.data.source.local.entity.FruitEntity

@Database(entities = [FruitEntity::class], version = 1, exportSchema = false)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object {
        @Volatile
        private var INSTANCE: FruitDatabase? = null

        fun getInstance(context: Context): FruitDatabase =
            INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FruitDatabase::class.java,
                "Fruit.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}