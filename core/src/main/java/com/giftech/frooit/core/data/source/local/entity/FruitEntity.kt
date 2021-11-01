package com.giftech.frooit.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruit")
data class FruitEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id:Int,

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "family")
    var family:String,

    @ColumnInfo(name = "genus")
    var genus:String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite:Boolean = false,
)