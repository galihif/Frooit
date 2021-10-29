package com.giftech.frooit.core.data.source.remote.network

import com.giftech.frooit.core.data.source.remote.response.FruitResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("fruit/all")
    fun getListFruit():Call<List<FruitResponse>>
}