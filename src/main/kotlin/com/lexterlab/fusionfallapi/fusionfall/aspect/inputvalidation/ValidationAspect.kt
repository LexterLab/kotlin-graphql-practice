package com.lexterlab.fusionfallapi.fusionfall.aspect.inputvalidation

import arrow.core.Either
import com.lexterlab.fusionfallapi.fusionfall.error.Error
import com.lexterlab.fusionfallapi.fusionfall.error.ErrorHandler
import com.lexterlab.fusionfallapi.fusionfall.exception.InputValidationException
import com.lexterlab.fusionfallapi.fusionfall.operations.base.OperationInput
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validator
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class ValidationAspect(
    private val validator: Validator,
    private val errorHandler: ErrorHandler
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(com.lexterlab.fusionfallapi.fusionfall.aspect.inputvalidation.ValidateInput)")
    @Throws(Throwable::class)
    fun validateInput(joinPoint: ProceedingJoinPoint): Any {
        val args = joinPoint.args

        if (args.size == 1 && args[0] is OperationInput) {
            val input = args[0] as OperationInput
            val violations = validator.validate(input)

            if (violations.isNotEmpty()) {
                val errors = mapConstraintViolations(violations)
                val validationException = InputValidationException(errors)

                val errorWrapper = errorHandler.handle(validationException)
                return Either.Left(errorWrapper)
            }
        }

        return joinPoint.proceed()
    }

    private fun mapConstraintViolations(violations: Set<ConstraintViolation<OperationInput>>): List<Error> {
        log.info("violations {}", violations)
        return violations.map { violation ->
            Error(
                message = violation.message,
                field = violation.propertyPath.toString()
            )
        }
    }
}