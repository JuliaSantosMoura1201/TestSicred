package com.example.presentation.utils

import com.example.domain.model.EventResponseBodyItem
import com.example.presentation.model.EventItem

fun EventResponseBodyItem.toPresentationEventItem() =
    EventItem(
        date = date.toDate(),
        description = description,
        id = id,
        image = image,
        latitude = "$LATITUDE$latitude",
        longitude = "$LONGITUDE$longitude",
        price = "$PRICE$price",
        title = title,
        contentToBeShare = createContentToBeShare()
    )

fun List<EventResponseBodyItem>.toPresentationEventItemList() =
    map {
        EventItem(
            date = it.date.toDate(),
            description = it.description,
            id = it.id,
            image = it.image,
            latitude = it.latitude.toString(),
            longitude = it.longitude.toString(),
            price = "$PRICE${it.price}",
            title = it.title,
            contentToBeShare = it.createContentToBeShare()
        )
    }

fun EventResponseBodyItem.createContentToBeShare() = title + "\n" + description + "\n" + date.toDate() + "\n" + "$PRICE$price"
private const val PRICE = "Preço R$"
private const val LATITUDE = "Lat: "
private const val LONGITUDE = "Long: "
