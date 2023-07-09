package com.rezapour.officemanager.features.roomlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.officemanager.utils.DataState
import com.rezapour.officemanager.base.dispatcher.DispatcherProvider
import com.rezapour.officemanager.features.mapper.UiItemMapper
import com.rezapour.officemanager.features.model.FactItem
import com.rezapour.officemanager.features.model.RoomItem
import com.rezapour.officemanager.domain.usecase.FilterUseCase
import com.rezapour.officemanager.domain.usecase.RoomFactUseCase
import com.rezapour.officemanager.domain.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomListViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase,
    private val filterUseCase: FilterUseCase,
    private val uiItemMapper: UiItemMapper,
    private val dispatcher: DispatcherProvider,
    private val factUseCase: RoomFactUseCase
) : ViewModel(), RoomListContract {

    private val _uiState: MutableStateFlow<DataState<List<RoomItem>>> =
        MutableStateFlow(DataState.Loading)

    override val uiState: StateFlow<DataState<List<RoomItem>>> = _uiState

    val filterState: StateFlow<Boolean> = filterUseCase.filterIsActive


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

    fun onMoreClicked(factItem: FactItem) {
        factUseCase.upDateFact(uiItemMapper.factItemToFact(factItem))
    }
}