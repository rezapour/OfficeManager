package com.rezapour.officemanager.features.filter

import androidx.lifecycle.ViewModel
import com.rezapour.officemanager.domain.model.FilterStatus
import com.rezapour.officemanager.features.mapper.UiItemMapper
import com.rezapour.officemanager.features.model.FilterViewOptions
import com.rezapour.officemanager.domain.usecase.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase,
    private val uiItemMapper: UiItemMapper
) : ViewModel() {
    private val _filterState: MutableStateFlow<FilterViewOptions> = MutableStateFlow(
        FilterViewOptions(department = emptyList(), type = emptyList())
    )

    val filterState: StateFlow<FilterViewOptions> = _filterState

    init {
        getFilters()
    }

    //TODO where get Filters
    private fun getFilters() {
        val filters = filterUseCase.getFilters()
        _filterState.value =
            FilterViewOptions(
                department = uiItemMapper.filterListToSelectionPotionList(filters.department),
                type = uiItemMapper.filterListToSelectionPotionList(filters.type)

            )
    }

    fun updateFilter(filterStatus: FilterStatus) {
        filterUseCase.updateFilter(filterStatus)
    }

    fun clearFilter() {
        filterUseCase.clearFilter()
    }
}