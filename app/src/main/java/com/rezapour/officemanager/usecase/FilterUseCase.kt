package com.rezapour.officemanager.usecase

import com.rezapour.officemanager.constant.Department
import com.rezapour.officemanager.constant.Type
import com.rezapour.officemanager.model.Filter
import com.rezapour.officemanager.model.FilterOption
import com.rezapour.officemanager.model.FilterStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

//TODO do not fier if is the same
//TODO multiClick fire
@Singleton
class FilterUseCase @Inject constructor() {

    private val _filterState = MutableStateFlow(FilterStatus("", ""))
    val filterState: StateFlow<FilterStatus> = _filterState

    private val _filterIsActive = MutableStateFlow(false)
    private val filterIsActive: StateFlow<Boolean> = _filterIsActive

    fun updateFilter(filter: FilterStatus) {
        _filterState.value = filter
        _filterIsActive.value = true
    }

    fun clearFilter() {
        _filterState.value = FilterStatus("", "")
        _filterIsActive.value = false
    }

    fun getFilters(): FilterOption {
        val department = Department.values().map { department ->
            Filter(department.value, department.value == _filterState.value.department)
        }

        val type =
            Type.values().map { type -> Filter(type.value, type.value == _filterState.value.type) }

        return FilterOption(department, type)
    }
}