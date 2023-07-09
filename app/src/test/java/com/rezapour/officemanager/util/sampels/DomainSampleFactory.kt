package com.rezapour.officemanager.util.sampels

import com.rezapour.officemanager.domain.model.Fact
import com.rezapour.officemanager.domain.model.Room

object DomainSampleFactory {

    fun getRooms(): List<Room> {
        val room1 = Room(
            roomNumber = "4.1-04",
            officeLevel = 4,
            name = "Play and fun room",
            department = "all",
            type = "public",
            id = "3DS8rJR6sXlIuRgMv5nZ"
        )

        val room2 = Room(
            roomNumber = "5.1-09",
            officeLevel = 5,
            name = "Customer support team",
            department = "support",
            type = "team",
            id = "3ebumztWsCslxWUc0U1Q",
            lovooFact = getLovooFact()
        )

        return listOf(room1, room2)
    }

    fun getLovooFact() = Fact(
        images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/lv-trialwork.appspot.com/o/rooms%2FIMG_7.jpg?alt=media",
        ),
        text = "Making our users happy is the main challenge for our support team",
        title = "Customer care"
    )
}