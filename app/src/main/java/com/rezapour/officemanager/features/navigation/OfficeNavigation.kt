package com.rezapour.officemanager.features.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rezapour.officemanager.features.filter.FilterScreen
import com.rezapour.officemanager.features.roomdetail.DetailScreen
import com.rezapour.officemanager.features.roomlist.RoomListScreen


fun NavGraphBuilder.officeNavigation(navController: NavController) {
    composable(Destinations.RoomListScreen.route) {
        RoomListScreen(
            onNavigateToFilterScreen = { navController.navigate(route = Destinations.FilterScreen.route) },
            onNavigateToDetailScreen = { navController.navigate(route = Destinations.RoomDetailScreen.route) })
    }

    composable(Destinations.RoomDetailScreen.route) {
        DetailScreen()
    }

    composable(Destinations.FilterScreen.route) {
        FilterScreen(onBackClicked = {navController.popBackStack()})
    }
}