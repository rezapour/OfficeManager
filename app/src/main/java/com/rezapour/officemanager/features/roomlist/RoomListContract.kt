package com.rezapour.officemanager.features.roomlist

import com.rezapour.officemanager.utils.DataState
import com.rezapour.officemanager.features.model.RoomItem
import kotlinx.coroutines.flow.StateFlow
//TODO Add interface to app
interface RoomListContract {

    val uiState: StateFlow<DataState<List<RoomItem>>>

    fun onRefresh()

}