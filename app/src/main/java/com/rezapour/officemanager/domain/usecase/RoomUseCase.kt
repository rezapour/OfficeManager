package com.rezapour.officemanager.domain.usecase

import com.rezapour.officemanager.utils.DataState
import com.rezapour.officemanager.data.exception.DataProviderException
import com.rezapour.officemanager.domain.repository.RoomRepository
import com.rezapour.officemanager.domain.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    private val _stateFlow: MutableStateFlow<DataState<List<Room>>> =
        MutableStateFlow(DataState.Loading)

    val stateFlow: StateFlow<DataState<List<Room>>> = _stateFlow

    suspend fun loadData(department: String, type: String) {
        _stateFlow.value = DataState.Loading
        try {
            _stateFlow.value = DataState.Success(roomRepository.getRooms(department, type))
        } catch (e: DataProviderException) {
            _stateFlow.value = DataState.Error(e.messageId)
        }
    }
}