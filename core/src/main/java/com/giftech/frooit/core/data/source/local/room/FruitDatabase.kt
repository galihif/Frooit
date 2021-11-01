package com.giftech.frooit.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giftech.frooit.core.data.source.local.entity.FruitEntity

@Database(entities = [com.giftech.frooit.core.data.source.local.entity.FruitEntity::class], version = 1, exportSchema = false)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun fruitDao(): com.giftech.frooit.core.data.source.local.room.FruitDao

    companion object {
        @Volatile
        private var INSTANCE: com.giftech.frooit.core.data.source.local.room.FruitDatabase? = null

        fun getInstance(context: Context): com.giftech.frooit.core.data.source.local.room.FruitDatabase =
            com.giftech.frooit.core.data.source.local.room.FruitDatabase.Companion.INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                com.giftech.frooit.core.data.source.local.room.FruitDatabase::class.java,
                "Fruit.db"
            )
                .fallbackToDestructiveMigration()
                .build()
            com.giftech.frooit.core.data.source.local.room.FruitDatabase.Companion.INSTANCE = instance
            instance
        }
    }
}