package com.rezapour.officemanager.features.roomlist

import com.rezapour.officemanager.DataState
import com.rezapour.officemanager.model.RoomItem
import kotlinx.coroutines.flow.StateFlow

interface RoomListContract {

    val uiState: StateFlow<DataState<List<RoomItem>>>

    fun onRefresh()

}