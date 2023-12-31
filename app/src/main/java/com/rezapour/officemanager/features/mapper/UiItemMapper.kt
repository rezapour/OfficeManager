package com.rezapour.officemanager.features.mapper

import com.rezapour.officemanager.domain.model.Fact
import com.rezapour.officemanager.features.model.FactItem
import com.rezapour.officemanager.domain.model.Filter
import com.rezapour.officemanager.domain.model.Room
import com.rezapour.officemanager.features.model.RoomItem
import com.rezapour.officemanager.features.model.SelectionOption
import javax.inject.Inject

class UiItemMapper @Inject constructor() {

    private fun roomToRoomItem(room: Room) =
        with(room) {
            RoomItem(
                roomNumber = roomNumber,
                officeLevel = officeLevel,
                name = name,
                department = department,
                type = type,
                id = id,
                lovooFact = if (lovooFact != null) factToFactItem(lovooFact) else null
            )
        }


    fun factToFactItem(fact: Fact) =
        with(fact) {
            FactItem(
                images = images,
                text = text,
                title = title
            )
        }

    fun factItemToFact(fact: FactItem) =
        with(fact) {
            Fact(
                images = images,
                text = text,
                title = title
            )
        }

    fun roomListToRoomItemList(roomList: List<Room>): List<RoomItem> =
        roomList.map { roomNetworkEntity -> roomToRoomItem(roomNetworkEntity) }


    private fun filterToSelectionOption(filter: Filter) =
        SelectionOption(option = filter.name, initialSelectedValue = filter.isSelected)

    fun filterListToSelectionPotionList(filters: List<Filter>): List<SelectionOption> =
        filters.map { filterToSelectionOption(it) }
}