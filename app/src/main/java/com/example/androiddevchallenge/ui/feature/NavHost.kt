package com.example.androiddevchallenge.ui.feature

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
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