package com.rezapour.officemanager.model


data class Room(
    val roomNumber: String,
    val officeLevel: Int,
    val name: String,
    val department: String,
    val type: String?,
    val id: String,
    val lovooFact: Fact? = null,
)

data class Fact(
    val images: List<String>,
    val text: String,
    val title: String
)
