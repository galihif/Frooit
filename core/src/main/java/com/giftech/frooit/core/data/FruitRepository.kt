package com.giftech.frooit.core.data

import com.giftech.frooit.core.data.source.local.LocalDataSource
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import com.giftech.frooit.core.domain.model.Fruit
import com.giftech.frooit.core.domain.repository.IFruitRepository
import com.giftech.frooit.core.utils.AppExecutors
import com.giftech.frooit.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FruitRepository(
    private val localDataSource: com.giftech.frooit.core.data.source.local.LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
):IFruitRepository{

    companion object {
        @Volatile
        private var instance: com.giftech.frooit.core.data.FruitRepository? = null

        fun getInstance(
            localData: com.giftech.frooit.core.data.source.local.LocalDataSource,
            remoteData: RemoteDataSource,
            appExecutors: AppExecutors
        ): com.giftech.frooit.core.data.FruitRepository =
            com.giftech.frooit.core.data.FruitRepository.Companion.instance ?: synchronized(this) {
                com.giftech.frooit.core.data.FruitRepository.Companion.instance
                    ?: com.giftech.frooit.core.data.FruitRepository(
                        localData,
                        remoteData,
                        appExecutors
                    )
            }
    }

    override fun getListFruit():Flow<com.giftech.frooit.core.data.Resource<List<Fruit>>>{
        return object : com.giftech.frooit.core.data.NetworkBoundResource<List<Fruit>, List<FruitResponse>>(){
            override fun loadFromDB(): Flow<List<Fruit>> {
                return localDataSource.getAllFruit().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Fruit>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<FruitResponse>>> {
                return remoteDataSource.getListFruit()
            }

            override suspend fun saveCallResult(data: List<FruitResponse>) {
                localDataSource.insertFruit(
                    DataMapper.mapResponseToEntity(data)
                )
            }

        }.asFlow()
    }

    override fun getFavoriteFruit():Flow<List<Fruit>>{
        return localDataSource.getFavoriteFruit().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteFruit(fruit:Fruit, state:Boolean){
        val fruitEntity = DataMapper.mapDomainToEntity(fruit)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteFruit(fruitEntity, state)
        }
    }

}