package com.example.androiddevchallenge.ui.feature.petlist

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PetListScreen(
    viewModel: PetListViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
}