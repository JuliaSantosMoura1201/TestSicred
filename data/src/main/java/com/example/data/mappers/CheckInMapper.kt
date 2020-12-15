package com.example.data.mappers

import com.example.domain.model.CheckInRequestBody

fun CheckInRequestBody.toDataCheckInRequestBody() =
    com.example.data.model.CheckInRequestBody(
        email = email!!,
        eventId = eventId!!,
        name = name!!
    )
