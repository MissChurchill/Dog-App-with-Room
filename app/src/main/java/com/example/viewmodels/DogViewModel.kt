package com.example.viewmodels

import androidx.lifecycle.*
import com.example.doggone.network.DogPhoto
import com.example.doggone.network.DogPhotoApi
import com.example.doggone.network.DogPhotoDao
import kotlinx.coroutines.launch

class DogViewModel(private val dogPhotoDao: DogPhotoDao) : ViewModel() {

    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto

    init {
        getNewPhoto()
    }

    fun getNewPhoto() {
        viewModelScope.launch {
            _dogPhoto.value = DogPhotoApi.retrofitService.getRandomPhoto()

        }
    }

    fun addDogImage(dogPhoto: DogPhoto) {
        viewModelScope.launch {
            dogPhotoDao.addDogImage(dogPhoto)

        }
    }
    fun deleteMostRecentDog(){
        viewModelScope.launch {
        val previousImage =dogPhotoDao.getMostRecentlyAddDog()
            dogPhotoDao.deleteDog()
        }
    }
    fun getAllDogs(): LiveData<List<DogPhoto>>
    {
       return dogPhotoDao.getAllDogImages().asLiveData()
    }

    }

class DogPhotoViewModelFactory(
        private val dogPhotoDao: DogPhotoDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DogViewModel(dogPhotoDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }





