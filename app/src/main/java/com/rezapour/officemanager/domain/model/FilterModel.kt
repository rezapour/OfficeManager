package com.rezapour.officemanager.domain.model

data class FilterStatus(val department: String, val type: String)
data class FilterOption(val department: List<Filter>, val type: List<Filter>)
data class Filter(val name: String, val isSelected: Boolean = false)