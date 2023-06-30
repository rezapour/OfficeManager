package com.rezapour.officemanager.data.network

import com.rezapour.officemanager.data.network.model.RoomNetworkEntity

interface ApiProvider {

    suspend fun getRooms(): List<RoomNetworkEntity>
}