package com.example.domain.di

import com.example.domain.usecases.CheckInUseCase
import com.example.domain.usecases.DetailEventUseCase
import com.example.domain.usecases.ListEventUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        ListEventUseCase(
            eventsRepository = get()
        )
    }

    factory {
        DetailEventUseCase(
            eventsRepository = get()
        )
    }

    factory {
        CheckInUseCase(
            checkInRepository = get()
        )
    }
}