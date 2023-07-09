package com.rezapour.officemanager.data.repository.impl

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.data.exception.DataProviderException
import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.mapper.NetworkMapper
import com.rezapour.officemanager.data.repository.RoomRepository
import com.rezapour.officemanager.util.MainCoroutineRule
import com.rezapour.officemanager.util.sampels.DomainSampleFactory
import com.rezapour.officemanager.util.sampels.NetworkSampleFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class RoomRepositoryImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var apiProvider: ApiProvider
    private lateinit var roomRepository: RoomRepository

    @Before
    fun before() {
        apiProvider = mock()
        roomRepository = RoomRepositoryImpl(apiProvider, NetworkMapper())
    }

    @Test
    fun getRoomsSuccessFullyTest() = runTest {
        whenever(
            apiProvider.getRooms(
                any(),
                any()
            )
        ).thenReturn(NetworkSampleFactory.getRoomResponse())
        assertThat(roomRepository.getRooms("", "")).isEqualTo(DomainSampleFactory.getRooms())


    }



    @Test
    fun getRoomsOnErrorTest() = runTest {
        whenever(
            apiProvider.getRooms(
                any(),
                any()
            )
        ).thenThrow(DataProviderException::class.java)

        Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                roomRepository.getRooms("", "")
            }
        }
    }
}