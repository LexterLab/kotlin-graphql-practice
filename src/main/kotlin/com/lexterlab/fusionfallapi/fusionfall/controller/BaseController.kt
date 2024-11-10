package com.lexterlab.fusionfallapi.fusionfall.controller

import arrow.core.Either
import com.lexterlab.fusionfallapi.fusionfall.error.ErrorWrapper
import com.lexterlab.fusionfallapi.fusionfall.operations.base.OperationOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class BaseController {

    fun handleOutput(output: Either<ErrorWrapper,  OperationOutput>, status: HttpStatus) : ResponseEntity<*> {
        return output.fold(
            { errorOutput -> ResponseEntity(errorOutput, errorOutput.status) },
            { operationOutput -> ResponseEntity(operationOutput, status) }
        )
    }

    fun  handleOutput(output: Either<ErrorWrapper, OperationOutput>) : Any {
        return output.fold(
            { errorOutput -> errorOutput },
            { operationOutput -> operationOutput}
        )
    }
}