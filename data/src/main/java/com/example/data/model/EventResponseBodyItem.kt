package com.example.data.model

data class EventResponseBodyItem(
    val date: Int,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val people: List<Any>,
    val price: Double,
    val title: String
)