package com.rezapour.officemanager.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rezapour.officemanager.features.navigation.Destinations
import com.rezapour.officemanager.features.navigation.officeNavigation

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Destinations.RoomListScreen.route,
        modifier = modifier
    ) {
        officeNavigation(navController=navController)
    }
}