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
package com.example.androiddevchallenge.ui.feature

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.feature.petdetail.PetDetailScreen
import com.example.androiddevchallenge.ui.feature.petdetail.PetDetailViewModel
import com.example.androiddevchallenge.ui.feature.petlist.PetListScreen
import com.example.androiddevchallenge.ui.feature.petlist.PetListViewModel

object ScreenRoute {
    const val PET_LIST = "petList"
    const val PET_DETAIL = "petDetail"
}

object ArgumentsKey {
    const val ID = "id"
}

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    petListViewModel: PetListViewModel = viewModel(),
    petDetailViewModel: PetDetailViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.PET_LIST
    ) {
        composable(
            route = ScreenRoute.PET_LIST,
        ) {
            PetListScreen(
                viewModel = petListViewModel,
                navController = navController
            )
        }

        composable(
            route = "${ScreenRoute.PET_DETAIL}/{${ArgumentsKey.ID}}",
            arguments = listOf(
                navArgument(ArgumentsKey.ID) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(ArgumentsKey.ID)?.let { id ->
                PetDetailScreen(
                    viewModel = petDetailViewModel,
                    id = id
                )
            }
        }
    }
}
