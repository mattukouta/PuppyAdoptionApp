package com.example.androiddevchallenge.ui.feature.petdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.model.Pet
import com.example.androiddevchallenge.data.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val petRepository: PetRepository
): ViewModel() {
    private var _pet: MutableLiveData<Pet> = MutableLiveData()
    val pet: LiveData<Pet> = _pet

    fun getPet(id: Int) = viewModelScope.launch(IO) {
        _pet.postValue(petRepository.getPet(id))
    }
}