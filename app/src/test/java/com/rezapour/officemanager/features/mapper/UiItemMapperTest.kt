package com.rezapour.officemanager.features.mapper

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import com.rezapour.officemanager.util.sampels.UiSampleFactory
import org.junit.Before
import org.junit.Test


class UiItemMapperTest {
    private lateinit var uiItemMapper: UiItemMapper

    @Before
    fun before() {
        uiItemMapper = UiItemMapper()
    }


    @Test
    fun factoToFactItemTest() {
        assertThat(uiItemMapper.factToFactItem(DomainSampleFactory.getLovooFact())).isEqualTo(
            UiSampleFactory.getLovooFact()
        )
    }

    @Test
    fun factItemToFactTest() {
        assertThat(uiItemMapper.factItemToFact(UiSampleFactory.getLovooFact())).isEqualTo(
            DomainSampleFactory.getLovooFact()
        )
    }

    @Test
    fun roomToRoomItemTest() {
        assertThat(uiItemMapper.roomListToRoomItemList(DomainSampleFactory.getRooms())).isEqualTo(
            UiSampleFactory.getRooms()
        )
    }

    @Test
    fun filterToSelctionOption(){
        assertThat(uiItemMapper.filterListToSelectionPotionList(DomainSampleFactory.getFilterDepartment())).isEqualTo(UiSampleFactory.getFilterDepartment())
    }
}