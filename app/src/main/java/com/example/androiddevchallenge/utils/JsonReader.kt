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
package com.example.androiddevchallenge.utils

import android.content.Context
import com.example.androiddevchallenge.data.model.Pet
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@Suppress("BlockingMethodInNonBlockingContext")
class JsonReader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) {
    private suspend fun readJsonFromAssets(fileName: String): String? =
        withContext(IO) {
            try {
                context
                    .assets
                    .open(fileName)
                    .bufferedReader()
                    .use { it.readText() }
            } catch (exception: IOException) {
                exception.printStackTrace()
                null
            }
        }

    private suspend fun readPetsJson(fileName: String): List<Pet> {
        val jsonBody = readJsonFromAssets(fileName) ?: throw NullPointerException()
        val type = Types.newParameterizedType(List::class.java, Pet::class.java)
        val adapter = moshi.adapter<List<Pet>>(type)
        return adapter.fromJson(jsonBody).orEmpty()
    }

    suspend fun getPets(fileName: String): List<Pet> = readPetsJson(fileName)

    suspend fun getPet(fileName: String, id: Int): Pet =
        readPetsJson(fileName).first {
            it.id == id
        }
}
