package com.example.doggone.viewmodels

import androidx.lifecycle.*
import com.example.doggone.database.DogImageEntity
import com.example.doggone.network.DogPhoto
import com.example.doggone.network.DogPhotoApi
import com.example.doggone.database.DogPhotoDao
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
    fun addDog(dogImageEntity: DogImageEntity) {
        viewModelScope.launch {
            dogPhotoDao.addDogImage(dogImageEntity)

        }
    }
    fun deleteMostRecentDog(){
        viewModelScope.launch {
            dogPhotoDao.deleteDog()
        }
    }
    fun getAllDogs(): LiveData<List<DogImageEntity>>
    {
       return dogPhotoDao.getAllDogImages().asLiveData()
    }
    }

class DogPhotoViewModelFactory(
        private val dogPhotoDao: DogPhotoDao
) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DogViewModel(dogPhotoDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }





