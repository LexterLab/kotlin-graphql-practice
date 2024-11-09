package com.lexterlab.fusionfallapi.fusionfall.exception

import com.lexterlab.fusionfallapi.fusionfall.error.Error

class InputValidationException(val errors: List<Error>): RuntimeException()