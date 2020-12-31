package com.example.kotlindogbreed.network

import com.example.kotlindogbreed.network.apiEndPoint.ApiService
import com.example.kotlindogbreed.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient{
    val service by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
}