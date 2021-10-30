package com.giftech.frooit.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.giftech.frooit.core.data.source.remote.network.ApiResponse
import com.giftech.frooit.core.data.source.remote.network.ApiService
import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getListFruit():LiveData<ApiResponse<List<FruitResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<FruitResponse>>>()

        apiService.getListFruit().enqueue(object : retrofit2.Callback<List<FruitResponse>>{
            override fun onResponse(
                call: Call<List<FruitResponse>>,
                response: Response<List<FruitResponse>>
            ) {
                if(response.isSuccessful){
                    val res = response.body()
                    resultData.value = if (res != null) ApiResponse.Success(res) else ApiResponse.Empty
                }
            }

            override fun onFailure(call: Call<List<FruitResponse>>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

        })

        return resultData
    }

}