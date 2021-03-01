package com.example.androiddevchallenge.ui.feature.petlist

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
class PetListViewModel @Inject constructor(
    private val petRepository: PetRepository
): ViewModel() {
    private var _pets: MutableLiveData<List<Pet>> = MutableLiveData(listOf())
    val pets: LiveData<List<Pet>> = _pets

    fun getPets() = viewModelScope.launch(IO) {
        _pets.postValue(petRepository.getPets())
    }
}