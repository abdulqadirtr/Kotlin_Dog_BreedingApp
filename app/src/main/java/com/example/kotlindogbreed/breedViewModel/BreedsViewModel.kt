package com.example.kotlindogbreed.breedViewModel

import androidx.lifecycle.liveData
import com.example.kotlindogbreed.breed_repository.BreedsRepository
import kotlinx.coroutines.Dispatchers

class BreedsViewModel: BaseViewModel(){
    val repository: BreedsRepository =
        BreedsRepository()
    val allBreeds = liveData(Dispatchers.IO){
        dataLoading.postValue(true)
        val retrieveBreeds= repository.getAllBreeds()
        dataLoading.postValue( false)
        if(retrieveBreeds.isSuccessful) {
            emit(retrieveBreeds.body()!!.message)
            empty.postValue( false)
        }else{
            empty.postValue(true)
        }
    }
}