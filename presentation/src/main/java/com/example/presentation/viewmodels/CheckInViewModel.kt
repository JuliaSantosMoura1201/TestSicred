package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.exceptions.FormException
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.model.CheckInRequestBody
import com.example.domain.usecases.CheckInUseCase
import com.example.presentation.R
import com.example.presentation.utils.StringLoader

class CheckInViewModel(
    private val checkInUseCase: CheckInUseCase,
    private val stringLoader: StringLoader
): BaseViewModel() {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> = _nameError

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit> = _success

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _noNetworking = MutableLiveData<Unit>()
    val noNetworking: LiveData<Unit> = _noNetworking

    fun makeCheckIn(checkInRequestBody: CheckInRequestBody?){
        launchSuspend {
            when(val result = checkInUseCase.execute(checkInRequestBody)){
                is Either.Success -> _success.postValue(Unit)
                is Either.Failure -> when(result.cause){
                    is FormException -> handlerFormException(result.cause as FormException)
                    is NoNetworkingException -> _noNetworking.postValue(Unit)
                    else -> _error.postValue(Unit)
                }
            }
        }
    }

    private fun handlerFormException(exception: FormException){
        when(exception){
            is FormException.EmptyNameException -> _nameError.postValue(stringLoader.get(R.string.empty_name_message))
            is FormException.EmptyEmailException -> _emailError.postValue(stringLoader.get(R.string.empty_email_message))
            is FormException.InvalidEmailException -> _emailError.postValue(stringLoader.get(R.string.invalid_email_message))
        }
    }
}
