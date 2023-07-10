package com.rezapour.officemanager.features.roomlist

import com.google.common.truth.Truth.assertThat
import com.rezapour.officemanager.base.TestDispatcherProvider
import com.rezapour.officemanager.base.dispatcher.DispatcherProvider
import com.rezapour.officemanager.domain.usecase.FilterUseCase
import com.rezapour.officemanager.domain.usecase.RoomFactUseCase
import com.rezapour.officemanager.domain.usecase.RoomUseCase
import com.rezapour.officemanager.features.mapper.UiItemMapper
import com.rezapour.officemanager.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.kotlin.mock
//TODO: the stateFlow should be mocked, at the time i could not find a way to mock it but i write the test cases with assertions.
@OptIn(ExperimentalCoroutinesApi::class)
class RoomListViewModelTest {

    private lateinit var viewModel: RoomListViewModel

    private val roomUseCase: RoomUseCase = mock()

    private val testScheduler = TestCoroutineScheduler()

    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(testScheduler)

    private val filterUseCase: FilterUseCase = mock()

    private val factUseCase: RoomFactUseCase = mock()


    @Test
    @Ignore
    fun loadDataLoading() = runTest {
//        whenever(roomUseCase.stateFlow.collect()).thenReturn(MutableStateFlow(DataState.Loading))

        viewModel = RoomListViewModel(
            roomUseCase,
            filterUseCase,
            UiItemMapper(),
            dispatcherProvider,
            factUseCase
        )
        val response = viewModel.uiState.value
        val filterStatus = viewModel.filterState.value

        assertThat(response).isInstanceOf(DataState.Loading::class.java)
        assertThat(filterStatus).isEqualTo(false)

    }

    @Test
    @Ignore
    fun loadDataSuccess() = runTest {
//        whenever(roomUseCase.stateFlow.collect()).thenReturn(MutableStateFlow(DataState.Success<Room>()))

        viewModel = RoomListViewModel(
            roomUseCase,
            filterUseCase,
            UiItemMapper(),
            dispatcherProvider,
            factUseCase
        )

        val response = viewModel.uiState.value
        val filterStatus = viewModel.filterState.value

        assertThat(response).isInstanceOf(DataState.Success::class.java)
        assertThat(filterStatus).isEqualTo(false)

    }

    @Test
    @Ignore
    fun loadDataError() = runTest {
//        whenever(roomUseCase.stateFlow.collect()).thenReturn(MutableStateFlow(DataState.Error))

        viewModel = RoomListViewModel(
            roomUseCase,
            filterUseCase,
            UiItemMapper(),
            dispatcherProvider,
            factUseCase
        )

        val response = viewModel.uiState.value
        val filterStatus = viewModel.filterState.value

        assertThat(response).isInstanceOf(DataState.Error::class.java)
        assertThat(filterStatus).isEqualTo(false)

    }

    @Test
    @Ignore
    fun filterChange() {
//        whenever(roomUseCase.stateFlow).thenReturn(DataState.Success<Room>(emptyList()))
//        whenever(filterUseCase.filterState).thenReturn(FilterStatus(department = "all", type = "meeting"))

        viewModel = RoomListViewModel(
            roomUseCase,
            filterUseCase,
            UiItemMapper(),
            dispatcherProvider,
            factUseCase
        )
        val response = viewModel.uiState.value
        val filterStatus = viewModel.filterState.value

        assertThat(response).isInstanceOf(DataState.Success::class.java)
        assertThat(filterStatus).isEqualTo(true)
    }
}

