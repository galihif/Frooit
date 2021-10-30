package com.giftech.frooit.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.giftech.frooit.core.data.source.local.LocalDataSource
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.utils.AppExecutors
import com.giftech.frooit.core.utils.DataMapper

class FruitRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
){

    companion object {
        @Volatile
        private var instance: FruitRepository? = null

        fun getInstance(
            localData: LocalDataSource,
            remoteData: RemoteDataSource,
            appExecutors: AppExecutors
        ): FruitRepository =
            instance ?: synchronized(this) {
                instance ?: FruitRepository(localData,remoteData,appExecutors)
            }
    }

    fun getListFruit():LiveData<Resource<List<Fruit>>>{
        return object : NetworkBoundResource<List<Fruit>, List<FruitResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<Fruit>> {
                return Transformations.map(localDataSource.getAllFruit()){
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Fruit>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<FruitResponse>>> {
                return remoteDataSource.getListFruit()
            }

            override fun saveCallResult(data: List<FruitResponse>) {
                localDataSource.insertFruit(
                    DataMapper.mapResponseToEntity(data)
                )
            }

        }.asLiveData()
    }

    fun getFavoriteFruit():LiveData<List<Fruit>>{
        return Transformations.map(localDataSource.getFavoriteFruit()){
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    fun setFavoriteFruit(fruit:Fruit, state:Boolean){
        val fruitEntity = DataMapper.mapDomainToEntity(fruit)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteFruit(fruitEntity, state)
        }
    }

}