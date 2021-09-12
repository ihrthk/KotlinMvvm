package com.example.kotlinmvvm

import androidx.lifecycle.LiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WanApi {

    @GET("banner/json")
    fun bannerList1(): Call<BannerList>

    @GET("banner/json")
    fun bannerList2(): LiveData<Result<BannerList>>


    companion object {
        fun get(): WanApi {
            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
            return Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .client(clientBuilder.build())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WanApi::class.java)
        }
    }
}