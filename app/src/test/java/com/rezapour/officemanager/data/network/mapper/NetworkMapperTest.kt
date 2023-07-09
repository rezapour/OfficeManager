package com.rezapour.officemanager.data.network.mapper

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import com.rezapour.officemanager.util.sampels.NetworkSampleFactory
import org.junit.Before
import org.junit.Test


class NetworkMapperTest{
    lateinit var mapper: NetworkMapper

    @Before
    fun setUp() {
        mapper = NetworkMapper()
    }

    @Test
    fun RoomNetworkEntityToRoomListTest(){
        assertThat(mapper.roomNetworkEntityListToRoomList(NetworkSampleFactory.getRoomResponse())).isEqualTo(DomainSampleFactory.getRoomResponse())
    }
}