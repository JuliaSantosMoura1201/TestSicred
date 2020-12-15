package com.example.data.repositoryimpl

import com.example.data.mappers.toDataCheckInRequestBody
import com.example.data.services.CheckInService
import com.example.domain.Either
import com.example.domain.exceptions.GenericException
import com.example.domain.exceptions.NoNetworkingException
import com.example.domain.model.CheckInRequestBody
import com.example.domain.repositories.CheckInRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class CheckInRepositoryImpl(
    private val checkInService: CheckInService
): CheckInRepository {

    override suspend fun makeCheckIn(checkInRequestBody: CheckInRequestBody): Either<Unit, Exception> =
        withContext(Dispatchers.IO) {
            try {
                val response = checkInService.makeCheckIn(
                    checkInRequestBody.toDataCheckInRequestBody()
                )
                when (response.code()) {
                    201 -> Either.Success(Unit)
                    else -> Either.Failure(GenericException)
                }
            } catch (e: UnknownHostException) {
                Either.Failure(NoNetworkingException)
            }
        }
}