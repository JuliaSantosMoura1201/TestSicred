package com.example.data.services

import com.example.data.model.EventResponseBodyItem
import com.example.data.model.ListEventsResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {

    @GET("events")
    suspend fun getListEvents(): Response<ListEventsResponseBody>

    @GET("events/{id}")
    suspend fun getEventById(
        @Path("id") id: String
    ): Response<EventResponseBodyItem>
}