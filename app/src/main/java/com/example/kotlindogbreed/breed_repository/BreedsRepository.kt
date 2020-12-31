package com.example.kotlindogbreed.breed_repository

import com.example.kotlindogbreed.network.RetrofitClient
import com.example.kotlindogbreed.network.apiEndPoint.ApiService


class BreedsRepository{

    var client : ApiService = RetrofitClient.service
    suspend fun getAllBreeds()= client.getAllBreeds()

    suspend fun getBreedImage(breedName:String) = client.getBreedImage(breedName)
}