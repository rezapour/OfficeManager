package com.rezapour.officemanager.data.network.model

import com.google.gson.annotations.Expose

data class RoomNetworkEntity(
    @Expose val roomNumber: String,
    @Expose val officeLevel: String,
    @Expose val name: String,
    @Expose val department: String,
    @Expose val type: String,
    @Expose val id: String,
    @Expose val lovooFact: LovooFactNetworkEntity? = null,
)

data class LovooFactNetworkEntity(
    @Expose val images: List<String>,
    @Expose val text: String,
    @Expose val title: String
)
