/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        jsonReader.getPet(ADOPRION_FILE_NAME, id)
}
