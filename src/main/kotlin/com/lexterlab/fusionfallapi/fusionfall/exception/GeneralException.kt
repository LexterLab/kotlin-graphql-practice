package com.lexterlab.fusionfallapi.fusionfall.exception

import org.springframework.http.HttpStatus

class GeneralException(val status: HttpStatus, message: String): RuntimeException(message)