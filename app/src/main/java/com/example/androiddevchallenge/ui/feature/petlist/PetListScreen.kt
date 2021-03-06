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
package com.example.androiddevchallenge.ui.feature.petlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.model.Pet
import com.example.androiddevchallenge.ui.feature.ScreenRoute
import com.example.androiddevchallenge.ui.theme.blue
import com.example.androiddevchallenge.ui.theme.red
import com.example.androiddevchallenge.ui.theme.shapes
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetListScreen(
    viewModel: PetListViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val pets by viewModel.pets.observeAsState(listOf())
    viewModel.getPets()

    PetList(
        pets = pets,
        onClicked = {
            navController.navigate(
                "${ScreenRoute.PET_DETAIL}/$it",
            )
        }
    )
}

@Composable
fun PetList(
    pets: List<Pet>,
    onClicked: (id: Int) -> Unit = {}
) {
    LazyColumn {
        items(pets) {
            PetItem(
                pet = it,
                onClicked = onClicked
            )
        }
    }
}

@Composable
fun PetItem(
    pet: Pet,
    onClicked: (id: Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClicked(pet.id)
            },
        elevation = 5.dp,
        shape = shapes.large
    ) {
        val genderColor = if (pet.gender == "male") blue else red

        Row {
            CoilImage(
                data = pet.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(
                        width = 200.dp,
                        height = 150.dp
                    ),
                alignment = Alignment.CenterStart
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = pet.name,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )

                Text(
                    text = pet.breed,
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp
                )

                Text(
                    text = pet.gender,
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp,
                    color = genderColor
                )
            }
        }
    }
}
