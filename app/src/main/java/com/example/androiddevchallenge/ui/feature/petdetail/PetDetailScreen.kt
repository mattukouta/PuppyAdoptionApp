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
package com.example.androiddevchallenge.ui.feature.petdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.model.Pet
import com.example.androiddevchallenge.ui.theme.contentColor
import com.example.androiddevchallenge.ui.theme.shapes
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel = viewModel(),
    id: Int = 0
) {
    val pet by viewModel.pet.observeAsState(Pet())
    viewModel.getPet(id)

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Card(
            shape = shapes.large
        ) {
            CoilImage(
                data = pet.image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Text(
            text = pet.name,
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "Breed : ${pet.breed}",
            fontSize = 20.sp,
            color = contentColor,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Gender : ${pet.gender}",
            fontSize = 20.sp,
            color = contentColor,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Description : ",
            fontSize = 20.sp,
            color = contentColor,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = pet.description,
            fontSize = 20.sp,
            color = contentColor,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}
