package com.example.domain.exceptions

sealed class FormException: Exception(){
    object EmptyEmailException: FormException()
    object InvalidEmailException: FormException()
    object EmptyNameException: FormException()
}
