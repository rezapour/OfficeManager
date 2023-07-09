package com.rezapour.officemanager.domain.usecase

import com.google.common.truth.Truth
import com.rezapour.officemanager.domain.repository.RoomRepository
import com.rezapour.officemanager.util.MainCoroutineRule
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import com.rezapour.officemanager.utils.DataState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class RoomFactUseCaseTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var roomFactUseCase: RoomFactUseCase

    @Before
    fun before() {
        roomFactUseCase = RoomFactUseCase()
    }

    @Test
    fun loadDataSuccessFull() = runTest {
        roomFactUseCase.upDateFact(DomainSampleFactory.getLovooFact())
        val result=roomFactUseCase.factState.value
        Truth.assertThat(result).isEqualTo(DomainSampleFactory.getLovooFact())
    }
}