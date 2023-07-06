package com.rezapour.officemanager.features.navigation

sealed class Destinations(val route: String) {
    object RoomListScreen : Destinations("room_list_screen")
    object RoomDetailScreen : Destinations("room_detail_screen")
    object FilterScreen : Destinations("filter_screen_screen")
}