package com.rezapour.officemanager.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.data.exception.DataProviderException
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


class RoomUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var roomUseCase: RoomUseCase
    lateinit var repository: RoomRepository

    @Before
    fun before() {
        repository = mock()
        roomUseCase = RoomUseCase(repository)
    }

    @Test
    fun loadDataSuccessFull() = runTest {
        whenever(repository.getRooms(any(), any())).thenReturn(DomainSampleFactory.getRooms())

        roomUseCase.loadData("", "")
        val result=roomUseCase.stateFlow.value
        assertThat(result).isInstanceOf(DataState.Success::class.java)
    }

    @Test
    fun loadDataOnError() = runTest {
        whenever(repository.getRooms(any(), any())).thenThrow(DataProviderException::class.java)

        roomUseCase.loadData("", "")
        val result=roomUseCase.stateFlow.value
        assertThat(result).isInstanceOf(DataState.Error::class.java)
    }


}