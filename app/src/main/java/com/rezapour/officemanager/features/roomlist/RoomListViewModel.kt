package com.rezapour.officemanager.features.roomlist

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.officemanager.DataState
import com.rezapour.officemanager.base.dispatcher.DispatcherProvider
import com.rezapour.officemanager.mapper.UiItemMapper
import com.rezapour.officemanager.model.RoomItem
import com.rezapour.officemanager.usecase.FilterUseCase
import com.rezapour.officemanager.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase,
    private val filterUseCase: FilterUseCase,
    private val uiItemMapper: UiItemMapper,
    private val dispatcher: DispatcherProvider
) : ViewModel(), RoomListContract {

    private val _uiState: MutableStateFlow<DataState<List<RoomItem>>> =
        MutableStateFlow(DataState.Loading)

    override val uiState: StateFlow<DataState<List<RoomItem>>> = _uiState

    init {
        viewModelScope.launch {
            roomUseCase.stateFlow.collect { dataState ->
                when (dataState) {
                    is DataState.Error -> _uiState.value = DataState.Error(dataState.messageId)
                    DataState.Loading -> _uiState.value = DataState.Loading
                    is DataState.Success -> {
                        _uiState.value =
                            DataState.Success(uiItemMapper.roomListToRoomItemList(dataState.data))
                    }
                }
            }
        }

        viewModelScope.launch {
            filterUseCase.filterState.collect { filters ->
                roomUseCase.loadData(filters.department, filters.type)
            }
        }
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(dispatcher.io) { roomUseCase.loadData("", "") }
    }

    override fun onRefresh() {
        loadData()
    }
}