package com.rezapour.officemanager.data.repository.impl

import android.util.Log
import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.mapper.NetworkMapper
import com.rezapour.officemanager.data.repository.RoomRepository
import com.rezapour.officemanager.model.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val apiProvider: ApiProvider,
    private val networkMapper: NetworkMapper
) : RoomRepository {
    override suspend fun getRooms(department: String, type: String): Flow<List<Room>> = flow {
        emit(networkMapper.roomNetworkEntityListToRoomList(apiProvider.getRooms(department, type)))
    }


}