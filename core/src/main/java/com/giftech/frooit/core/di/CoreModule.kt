package com.giftech.frooit.core.di

import androidx.room.Room
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.source.local.LocalDataSource
import com.giftech.frooit.core.data.source.local.room.FruitDatabase
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiService
import com.giftech.frooit.core.domain.repository.IFruitRepository
import com.giftech.frooit.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<com.giftech.frooit.core.data.source.local.room.FruitDatabase>().fruitDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            com.giftech.frooit.core.data.source.local.room.FruitDatabase::class.java, "Fruit.db"
        ).fallbackToDestructiveMigration().build()
    }
}

const val BASE_URL = "https://www.fruityvice.com/api/"

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.giftech.frooit.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFruitRepository> { com.giftech.frooit.core.data.FruitRepository(get(), get(), get()) }
}