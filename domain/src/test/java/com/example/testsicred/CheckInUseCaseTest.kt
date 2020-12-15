package com.example.testsicred

import assertk.assertions.isEqualTo
import com.example.domain.Either
import com.example.domain.exceptions.FormException
import com.example.domain.model.CheckInRequestBody
import com.example.domain.repositories.CheckInRepository
import com.example.domain.usecases.CheckInUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CheckInUseCaseTest {

    private val checkInRepository = mockk<CheckInRepository>()
    private val subject = CheckInUseCase(checkInRepository)

    @Test
    fun `check if when checkInRequestBody has empty email returns EmptyEmailException `() =
        runBlocking {
            //when
            val result = subject.execute(
                checkInRequestBody = checkInRequestModelWithEmptyEmail
            )

            //then
            assertk.assertThat(result).isEqualTo(Either.Failure(FormException.EmptyEmailException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithEmptyEmail
                )
            }
        }

    @Test
    fun `check if when checkInRequestBody has invalid email returns InvalidEmailException `() =
        runBlocking {
            //when
            val result = subject.execute(
                checkInRequestBody = checkInRequestModelWithInvalidEmail
            )

            //then
            assertk.assertThat(result).isEqualTo(Either.Failure(FormException.InvalidEmailException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithInvalidEmail
                )
            }
        }

    @Test
    fun `check if when checkInRequestBody has empty name returns EmptyNameException `() =
        runBlocking {
            //when
            val result = subject.execute(
                checkInRequestBody = checkInRequestModelWithIEmptyName
            )

            //then
            assertk.assertThat(result).isEqualTo(Either.Failure(FormException.EmptyNameException))
            coVerify(exactly = 0) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = checkInRequestModelWithIEmptyName
                )
            }
        }

    @Test
    fun `check if when everything is correct returns Unit `() =
        runBlocking {
            //given
            coEvery {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = validCheckInRequestModel
                )
            } returns Either.Success(Unit)

            //when
            val result = subject.execute(
                checkInRequestBody = validCheckInRequestModel
            )

            //then
            assertk.assertThat(result).isEqualTo(Either.Success(Unit))
            coVerify(exactly = 1) {
                checkInRepository.makeCheckIn(
                    checkInRequestBody = validCheckInRequestModel
                )
            }
        }

    companion object {
        private const val EMPTY_FIELD = ""
        private const val VALID_EMAIL = "test@test.com"
        private const val INVALID_EMAIL = "testTest.com"
        private const val VALID_ID = "1"
        private const val VALID_NAME = "test"
        private val checkInRequestModelWithEmptyEmail =
            CheckInRequestBody(
                EMPTY_FIELD,
                VALID_ID,
                VALID_NAME
            )
        private val checkInRequestModelWithInvalidEmail =
            CheckInRequestBody(
                INVALID_EMAIL,
                VALID_ID,
                VALID_NAME
            )
        private val checkInRequestModelWithIEmptyName =
            CheckInRequestBody(
                VALID_EMAIL,
                VALID_ID,
                EMPTY_FIELD
            )
        private val validCheckInRequestModel =
            CheckInRequestBody(
                VALID_EMAIL,
                VALID_ID,
                VALID_NAME
            )
    }
}
