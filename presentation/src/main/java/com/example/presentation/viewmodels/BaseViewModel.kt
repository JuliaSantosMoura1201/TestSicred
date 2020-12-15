package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(){

    fun launchSuspend(block: suspend  () -> Unit){
        viewModelScope.launch {
            block()
        }
    }
}