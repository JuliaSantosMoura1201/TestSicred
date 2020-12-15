package com.example.domain.usecases

import com.example.domain.Either
import com.example.domain.model.EventResponseBodyItem
import com.example.domain.repositories.EventsRepository

class DetailEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend fun execute(id: String?): Either<EventResponseBodyItem, Exception>{
        if (id.isNullOrEmpty()) throw IllegalArgumentException()
        return eventsRepository.getEventById(id)
    }
}
