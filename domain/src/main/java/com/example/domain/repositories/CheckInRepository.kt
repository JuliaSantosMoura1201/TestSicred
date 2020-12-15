package com.example.domain.repositories

import com.example.domain.Either
import com.example.domain.model.CheckInRequestBody

interface CheckInRepository {

    suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception>
}
