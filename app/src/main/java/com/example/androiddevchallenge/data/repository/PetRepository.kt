package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.model.Pet
import com.example.androiddevchallenge.utils.JsonReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepository @Inject constructor(
    private val jsonReader: JsonReader
) {
    val ADOPRION_FILE_NAME = "adoption_data.json"

    suspend fun getPets(): List<Pet> =
        jsonReader.getPets(ADOPRION_FILE_NAME)

    suspend fun getPet(id: Int): Pet =
        jsonReader.getPet(ADOPRION_FILE_NAME ,id)
}