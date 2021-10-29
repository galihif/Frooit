package com.giftech.frooit.core.di

import android.content.Context
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiConfig

object Injection {

    fun provideRepository(context: Context):FruitRepository{
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val fruitRepository = FruitRepository.getInstance(remoteDataSource)

        return fruitRepository
    }

}