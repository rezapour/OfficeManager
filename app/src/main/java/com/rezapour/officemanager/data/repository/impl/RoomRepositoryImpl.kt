package com.rezapour.officemanager.data.repository.impl

import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.mapper.NetworkMapper
import com.rezapour.officemanager.domain.repository.RoomRepository
import com.rezapour.officemanager.domain.model.Room
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val apiProvider: ApiProvider,
    private val networkMapper: NetworkMapper
) : RoomRepository {
    override suspend fun getRooms(department: String, type: String): List<Room> {
        return networkMapper.roomNetworkEntityListToRoomList(apiProvider.getRooms(department, type))
    }


}