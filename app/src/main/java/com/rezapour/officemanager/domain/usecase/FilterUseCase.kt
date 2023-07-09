package com.rezapour.officemanager.domain.usecase

import com.rezapour.officemanager.base.constant.Department
import com.rezapour.officemanager.base.constant.Type
import com.rezapour.officemanager.domain.model.Filter
import com.rezapour.officemanager.domain.model.FilterOption
import com.rezapour.officemanager.domain.model.FilterStatus
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
    val filterIsActive: StateFlow<Boolean> = _filterIsActive

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