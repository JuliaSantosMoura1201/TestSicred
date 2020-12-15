package com.example.domain.usecases

import com.example.domain.Either
import com.example.domain.exceptions.FormException
import com.example.domain.isValidEmail
import com.example.domain.model.CheckInRequestBody
import com.example.domain.repositories.CheckInRepository

class CheckInUseCase(
    private val checkInRepository: CheckInRepository
) {

    suspend fun execute(checkInRequestBody: CheckInRequestBody?): Either<Unit, Exception>{
        if (checkInRequestBody == null) throw IllegalArgumentException()
        if (checkInRequestBody.eventId == null)  throw IllegalArgumentException()
        return  when {
            checkInRequestBody.email.isNullOrEmpty() -> Either.Failure(FormException.EmptyEmailException)
            !isValidEmail(checkInRequestBody.email) -> Either.Failure(FormException.InvalidEmailException)
            checkInRequestBody.name.isNullOrEmpty() -> Either.Failure(FormException.EmptyNameException)
            else -> checkInRepository.makeCheckIn(checkInRequestBody)
        }
    }
}