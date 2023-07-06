package com.rezapour.officemanager.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class RoomItem(
    val roomNumber: String,
    val officeLevel: Int,
    val name: String,
    val department: String,
    val type: String?,
    val id: String,
    val lovooFact: FactItem? = null,
)

data class FactItem(
    val images: List<String>,
    val text: String,
    val title: String
)

data class SelectionOption(val option: String, var initialSelectedValue: Boolean) {
    var selected by mutableStateOf(initialSelectedValue)
}

data class FilterViewOptions(val department: List<SelectionOption>, val type: List<SelectionOption>)

