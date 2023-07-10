package com.rezapour.officemanager.data.network.mapper

import com.rezapour.officemanager.data.network.model.FactNetworkEntity
import com.rezapour.officemanager.data.network.model.RoomNetworkEntity
import com.rezapour.officemanager.domain.model.Fact
import com.rezapour.officemanager.domain.model.Room
import javax.inject.Inject

class NetworkMapper @Inject constructor() {

    private fun roomNetworkEntityToRoom(networkEntity: RoomNetworkEntity) =
        with(networkEntity) {
            Room(
                roomNumber = roomNumber,
                officeLevel = officeLevel,
                name = name,
                department = department,
                type = type,
                id = id,
                lovooFact = if (lovooFact != null) factNetworkEntityToFact(lovooFact) else null
            )
        }


    private fun factNetworkEntityToFact(networkEntity: FactNetworkEntity) =
        with(networkEntity) {
            Fact(
                images = images,
                text = text,
                title = title
            )
        }

    fun roomNetworkEntityListToRoomList(networkEntityList: List<RoomNetworkEntity>): List<Room> =
        networkEntityList.map { roomNetworkEntity -> roomNetworkEntityToRoom(roomNetworkEntity) }
}