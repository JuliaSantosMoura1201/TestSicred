package com.example.domain.repositories

import com.example.domain.Either
import com.example.domain.model.EventResponseBodyItem

interface EventsRepository {

    suspend fun listEvents(): Either<List<EventResponseBodyItem>, Exception>

    suspend fun getEventById(id: String): Either<EventResponseBodyItem, Exception>
}