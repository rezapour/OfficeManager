package com.rezapour.officemanager.features.filter

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.domain.usecase.FilterUseCase
import com.rezapour.officemanager.features.mapper.UiItemMapper
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import com.rezapour.officemanager.util.sampels.UiSampleFactory
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class FilterViewModelTest {

    private lateinit var filterViewModel: FilterViewModel


    private lateinit var filterUseCase: FilterUseCase

    @Before
    fun before() {
        filterUseCase = mock()
    }

    @Test
    fun getFilters() {
        whenever(filterUseCase.getFilters()).thenReturn(DomainSampleFactory.getFilterOption())
        filterViewModel = FilterViewModel(filterUseCase, UiItemMapper())

        val filters = filterViewModel.filterState.value

        assertThat(filters).isEqualTo(UiSampleFactory.getFilterOption())
    }
}