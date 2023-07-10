package com.rezapour.officemanager.data.network.retrofit

import com.rezapour.officemanager.data.network.model.RoomNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("lovooOffice")
    suspend fun getRooms(
        @Query("department") department: String,
        @Query("type") type: String
    ): Response<List<RoomNetworkEntity>>

}