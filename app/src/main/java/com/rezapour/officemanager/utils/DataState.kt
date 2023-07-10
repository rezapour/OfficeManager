package com.rezapour.officemanager.utils

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val messageId: Int) : DataState<T>()
}