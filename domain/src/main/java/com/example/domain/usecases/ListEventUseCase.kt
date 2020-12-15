package com.example.domain.usecases

import com.example.domain.Either
import com.example.domain.model.EventResponseBodyItem
import com.example.domain.repositories.EventsRepository

class ListEventUseCase(
    private val eventsRepository: EventsRepository
) {

    suspend fun execute(): Either<List<EventResponseBodyItem>, Exception> =
        eventsRepository.listEvents()
}
