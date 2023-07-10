package com.rezapour.officemanager.data.network.impl

import retrofit2.Response

internal fun <T> Response<T>.isResponseValid(): Boolean {
    return body() != null
}