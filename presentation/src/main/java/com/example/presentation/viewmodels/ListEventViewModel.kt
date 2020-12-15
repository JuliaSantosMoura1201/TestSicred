package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.usecases.ListEventUseCase
import com.example.presentation.model.EventItem
import com.example.presentation.utils.toPresentationEventItemList

class ListEventViewModel(
    private val listEventUseCase: ListEventUseCase
): BaseViewModel() {

    private val _listOfEvents = MutableLiveData<List<EventItem>>()
    val listOfEvents: LiveData<List<EventItem>> = _listOfEvents

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun getListEvents(){
        launchSuspend {
            when(val result = listEventUseCase.execute()){
                is Either.Success -> _listOfEvents.postValue(result.data.toPresentationEventItemList())
                is Either.Failure -> when(result.cause){
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }
}
