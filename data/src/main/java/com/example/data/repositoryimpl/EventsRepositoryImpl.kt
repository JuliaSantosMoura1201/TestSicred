package com.example.data.repositoryimpl

import com.example.data.mappers.toDomainEventResponseBodyItem
import com.example.data.mappers.toDomainListEventsItemsListResponseBody
import com.example.data.services.EventsService
import com.example.domain.Either
import com.example.domain.exceptions.GenericException
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.model.EventResponseBodyItem
import com.example.domain.repositories.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class EventsRepositoryImpl(
    private val eventsService: EventsService
): EventsRepository {

    override suspend fun listEvents(): Either<List<EventResponseBodyItem>, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = eventsService.getListEvents()
                when (response.code()) {
                    200 -> Either.Success(
                        response.body()!!.toDomainListEventsItemsListResponseBody()
                    )
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }

    override suspend fun getEventById(id: String): Either<EventResponseBodyItem, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = eventsService.getEventById(id)
                when (response.code()) {
                    200 -> Either.Success(
                        response.body()!!.toDomainEventResponseBodyItem()
                    )
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }
}
