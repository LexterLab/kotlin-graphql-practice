package com.lexterlab.fusionfallapi.fusionfall.operations.base

import arrow.core.Either
import com.lexterlab.fusionfallapi.fusionfall.error.ErrorWrapper

interface OperationProcessor<in I: OperationInput, out O: OperationOutput> {
    fun process(input: I): Either<ErrorWrapper, O>
}