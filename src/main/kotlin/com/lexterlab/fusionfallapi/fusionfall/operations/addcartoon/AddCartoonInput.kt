package com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon

import com.lexterlab.fusionfallapi.fusionfall.operations.base.OperationInput
import com.lexterlab.fusionfallapi.fusionfall.utils.ValidationMessages.CARTOON_NAME_NOT_BLANK
import jakarta.validation.constraints.NotBlank

data class AddCartoonInput(
    @field:NotBlank(message = CARTOON_NAME_NOT_BLANK)
    val name: String?
): OperationInput