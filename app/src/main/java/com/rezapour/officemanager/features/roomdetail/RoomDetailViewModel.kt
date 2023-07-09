package com.rezapour.officemanager.features.roomdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezapour.officemanager.base.dispatcher.DispatcherProvider
import com.rezapour.officemanager.features.mapper.UiItemMapper
import com.rezapour.officemanager.features.model.FactItem
import com.rezapour.officemanager.domain.usecase.RoomFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDetailViewModel @Inject constructor(
    private val factUseCase: RoomFactUseCase,
    private val uiItemMapper: UiItemMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _factState: MutableStateFlow<FactItem> =
        MutableStateFlow(FactItem(emptyList<String>(), "", ""))
    val factState: StateFlow<FactItem> = _factState

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            factUseCase.factState.collect { fact ->
                _factState.value = uiItemMapper.factToFactItem(fact)
            }
        }
    }
}