package com.rezapour.officemanager.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.util.MainCoroutineRule
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FilterUseCaseTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var filterUseCase: FilterUseCase

    @Before
    fun before() {
        filterUseCase = FilterUseCase()
    }

    @Test
    fun updateFilter() {
        filterUseCase.updateFilter(DomainSampleFactory.getFilterStatus())

        val filterActive=filterUseCase.filterIsActive.value
        val filterStatus=filterUseCase.filterState.value

        assertThat(filterActive).isEqualTo(true)
        assertThat(filterStatus).isEqualTo(DomainSampleFactory.getFilterStatus())
    }

    @Test
    fun clearFilter() {
        filterUseCase.clearFilter()

        val filterActive=filterUseCase.filterIsActive.value
        val filterStatus=filterUseCase.filterState.value

        assertThat(filterActive).isEqualTo(false)
        assertThat(filterStatus).isEqualTo(DomainSampleFactory.clearFilterStatus())
    }

}