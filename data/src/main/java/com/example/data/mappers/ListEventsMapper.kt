package com.example.data.mappers

import com.example.data.model.ListEventsResponseBody
import com.example.domain.model.EventResponseBodyItem

fun ListEventsResponseBody.toDomainListEventsItemsListResponseBody() =
    map {
        EventResponseBodyItem(
            date = it.date,
            description = it.description,
            id = it.id,
            image = it.image,
            latitude = it.latitude,
            longitude = it.longitude,
            people = it.people,
            price = it.price,
            title = it.title
        )
    }

fun com.example.data.model.EventResponseBodyItem.toDomainEventResponseBodyItem() =
    EventResponseBodyItem(
        date = date,
        description = description,
        id = id,
        image = image,
        latitude = latitude,
        longitude = longitude,
        people = people,
        price = price,
        title = title
    )
