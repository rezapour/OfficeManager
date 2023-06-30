package com.rezapour.officemanager.data.network.retrofit

import com.rezapour.officemanager.data.network.model.RoomNetworkEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("lovooOffice")
    suspend fun getRooms(): Response<List<RoomNetworkEntity>>

}