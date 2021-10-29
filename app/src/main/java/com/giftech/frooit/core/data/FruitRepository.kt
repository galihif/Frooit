package com.giftech.frooit.core.data

import androidx.lifecycle.LiveData
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.response.FruitResponse

class FruitRepository private constructor(
    private val remoteDataSource: RemoteDataSource
){

    companion object {
        @Volatile
        private var instance: FruitRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource
        ): FruitRepository =
            instance ?: synchronized(this) {
                instance ?: FruitRepository(remoteData)
            }
    }

    fun getListFruit():LiveData<List<FruitResponse>>{
        return remoteDataSource.getListFruit()
    }

}