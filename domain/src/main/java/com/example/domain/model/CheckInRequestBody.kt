package com.example.domain.model

data class CheckInRequestBody(
    val email: String?,
    val eventId: String?,
    val name: String?
)