package com.rezapour.officemanager.data.network.impl

import com.rezapour.officemanager.data.exception.DataProviderException
import com.rezapour.officemanager.data.network.ApiProvider
import com.rezapour.officemanager.data.network.exception.ExceptionMapper
import com.rezapour.officemanager.data.network.model.RoomNetworkEntity
import com.rezapour.officemanager.data.network.retrofit.ApiService

class ApiProviderImpl(private val apiService: ApiService) : ApiProvider {
    override suspend fun getRooms(department: String, type: String): List<RoomNetworkEntity> {
        try {
            val assetResponse = apiService.getRooms(department, type)
            if (assetResponse.isSuccessful)
                if (assetResponse.isResponseValid())
                    return assetResponse.body()!!
                else
                    throw DataProviderException(ExceptionMapper.toRespondIsEmpty())
            else
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(assetResponse.code()))
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }
}