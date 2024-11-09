package com.lexterlab.fusionfallapi.fusionfall.error

import com.lexterlab.fusionfallapi.fusionfall.exception.GeneralException
import com.lexterlab.fusionfallapi.fusionfall.exception.InputValidationException
import com.lexterlab.fusionfallapi.fusionfall.operations.base.OperationInput
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validator
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ErrorHandler(private val validator: Validator) {

    fun handle(throwable: Throwable): ErrorWrapper {
        return when (throwable) {
            is GeneralException -> ErrorWrapper(
                errors = listOf(Error(message = throwable.message ?: "Unknown error")),
                status = throwable.status
            )
            is InputValidationException -> ErrorWrapper(
                errors = mapExceptionToErrors(throwable),
                status = HttpStatus.BAD_REQUEST
            )
            else -> ErrorWrapper(
                errors = listOf(Error(throwable.message ?: "Internal error")),
                status = HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    fun validateInput(input: OperationInput) {
        val violations = validator.validate(input)
        if (violations.isNotEmpty()) {
            throw InputValidationException(mapConstraintViolations(violations))
        }
    }

    private fun mapConstraintViolations(violations: Set<ConstraintViolation<OperationInput>>): List<Error> {
        return violations.map { violation ->
            Error(
                message = violation.message,
                field = violation.propertyPath.toString()
            )
        }
    }

    private fun mapExceptionToErrors(exception: InputValidationException): List<Error> {
        return exception.errors.map { error ->
            Error(
                message = error.message,
                field = error.field
            )
        }
    }
}