package com.giftech.frooit.core.di

import android.content.Context
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.source.local.LocalDataSource
import com.giftech.frooit.core.data.source.local.room.FruitDatabase
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiConfig
import com.giftech.frooit.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context):FruitRepository{
        val database = FruitDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.fruitDao())
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val appExecutors = AppExecutors()

        return FruitRepository.getInstance(localDataSource,remoteDataSource, appExecutors)
    }

}