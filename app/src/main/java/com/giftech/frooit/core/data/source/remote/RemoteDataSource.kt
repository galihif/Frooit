package com.giftech.frooit.core.data.source.remote

import android.util.Log
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.network.ApiService
import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getListFruit():Flow<ApiResponse<List<FruitResponse>>> {
        return flow{
            try{
                val response = apiService.getListFruit()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}