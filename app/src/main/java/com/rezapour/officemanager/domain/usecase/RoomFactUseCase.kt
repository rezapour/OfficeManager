package com.rezapour.officemanager.domain.usecase

import com.rezapour.officemanager.domain.model.Fact
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