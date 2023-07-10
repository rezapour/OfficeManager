package com.rezapour.officemanager.domain.repository

import com.rezapour.officemanager.domain.model.Room

interface RoomRepository {

    suspend fun getRooms(department: String, type: String): List<Room>
}