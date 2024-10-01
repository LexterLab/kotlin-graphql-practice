package com.lexterlab.fusionfallapi.fusionfall.operations.base

interface OperationProcessor<in I: OperationInput, out O: OperationOutput> {
    fun process(operation: I): O
}