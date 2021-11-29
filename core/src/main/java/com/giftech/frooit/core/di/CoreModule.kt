package com.giftech.frooit.core.di

import androidx.room.Room
import com.giftech.frooit.core.data.FruitRepository
import com.giftech.frooit.core.data.source.local.LocalDataSource
import com.giftech.frooit.core.data.source.local.room.FruitDatabase
import com.giftech.frooit.core.data.source.remote.RemoteDataSource
import com.giftech.frooit.core.data.source.remote.network.ApiService
import com.giftech.frooit.core.domain.repository.IFruitRepository
import com.giftech.frooit.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FruitDatabase>().fruitDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("giftech".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FruitDatabase::class.java, "Fruit.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

const val BASE_URL = "https://www.fruityvice.com/api/"

val networkModule = module {
    single {
        val hostname = "www.fruityvice.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/RNwUtLvbGWvI9P4kIRbB1+JXCua7dbJGsmDdXNCBHD8=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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