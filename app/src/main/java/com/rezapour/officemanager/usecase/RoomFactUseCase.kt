package com.rezapour.officemanager.usecase

import com.rezapour.officemanager.model.Fact
import com.rezapour.officemanager.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomFactUseCase @Inject constructor() {
    private val _factState: MutableStateFlow<Fact> = MutableStateFlow(Fact(emptyList(), "", ""))
    val factState: StateFlow<Fact> = _factState

    fun upDateFact(fact: Fact) {
        _factState.value = fact
    }
}