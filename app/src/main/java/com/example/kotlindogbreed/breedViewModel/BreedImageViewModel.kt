package com.example.kotlindogbreed.breedViewModel

import androidx.lifecycle.liveData
import com.example.kotlindogbreed.breed_repository.BreedsRepository
import kotlinx.coroutines.Dispatchers

class BreedImageViewModel : BaseViewModel() {
    val repository: BreedsRepository =
        BreedsRepository()
    fun breedImage(breedName:String)  = liveData(Dispatchers.IO){
        dataLoading.postValue(true)
        val retrieveBreedImage= repository.getBreedImage(breedName)
        if(retrieveBreedImage.isSuccessful) {
            emit(retrieveBreedImage.body()!!.message)
            empty.postValue(false)
        }else{
            empty.postValue(true)
        }
    }
}
