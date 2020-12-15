package com.example.presentation.di

import com.example.presentation.utils.StringLoader
import com.example.presentation.viewmodels.CheckInViewModel
import com.example.presentation.viewmodels.DetailEventViewModel
import com.example.presentation.viewmodels.ListEventViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentationModule = module{

    single {
        StringLoader(androidContext())
    }

    viewModel{
        ListEventViewModel(
             listEventUseCase = get()
        )
    }

    viewModel {
        DetailEventViewModel(
            detailEventUseCase = get()
        )
    }

    viewModel {
        CheckInViewModel(
            checkInUseCase = get(),
            stringLoader = get()
        )
    }
}
