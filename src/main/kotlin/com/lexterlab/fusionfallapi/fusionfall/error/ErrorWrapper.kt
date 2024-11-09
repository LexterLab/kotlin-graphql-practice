package com.lexterlab.fusionfallapi.fusionfall.error

import org.springframework.http.HttpStatus

data class ErrorWrapper(val errors: List<Error>, val status: HttpStatus)
