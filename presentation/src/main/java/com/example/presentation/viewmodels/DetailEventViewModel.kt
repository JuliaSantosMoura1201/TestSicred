package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.usecases.DetailEventUseCase
import com.example.presentation.model.EventItem
import com.example.presentation.utils.toPresentationEventItem

class DetailEventViewModel(
    private val detailEventUseCase: DetailEventUseCase
) : BaseViewModel() {

    private val _event = MutableLiveData<EventItem>()
    val event: LiveData<EventItem> = _event

    private val _contentToBeShare = MutableLiveData<String>()
    val contentToBeShare: LiveData<String> = _contentToBeShare

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun getEvent(id: String?) {
        launchSuspend {
            when (val result = detailEventUseCase.execute(id)) {
                is Either.Success -> _event.postValue(result.data.toPresentationEventItem())
                is Either.Failure -> when (result.cause) {
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }

    fun shareContent() {
        if (_event.value == null) {
            _error.postValue(Unit)
        } else {
            _contentToBeShare.postValue(_event.value!!.contentToBeShare)
        }
    }
}