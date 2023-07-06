package com.rezapour.officemanager.usecase

import android.util.Log
import com.rezapour.officemanager.DataState
import com.rezapour.officemanager.data.exception.DataProviderException
import com.rezapour.officemanager.data.repository.RoomRepository
import com.rezapour.officemanager.model.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class RoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    private val _stateFlow: MutableStateFlow<DataState<List<Room>>> =
        MutableStateFlow(DataState.Loading)

    val stateFlow: StateFlow<DataState<List<Room>>> = _stateFlow

    suspend fun loadData(department: String, type: String) {
        _stateFlow.value = DataState.Loading
        roomRepository.getRooms(department, type)
            .catch { e ->
                _stateFlow.value = DataState.Error((e as DataProviderException).messageId)
            }
            .collect { rooms ->
                _stateFlow.value = DataState.Success(rooms)
            }
    }


}