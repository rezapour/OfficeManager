package com.rezapour.officemanager.data.repository

import com.rezapour.officemanager.model.Room
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun getRooms(department: String, type: String): Flow<List<Room>>
}