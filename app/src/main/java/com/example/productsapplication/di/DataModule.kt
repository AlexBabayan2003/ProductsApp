package com.example.productsapplication.di

import android.content.Context
import androidx.room.Room
import com.example.productsapplication.data.local.ProductsDao
import com.example.productsapplication.data.local.ProductsDb
import com.example.productsapplication.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            client
        )
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductsDb =
        Room.databaseBuilder(
            context,
            ProductsDb::class.java,
            "products_db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideProductsDao(db: ProductsDb): ProductsDao =
        db.getProductsDao()
}