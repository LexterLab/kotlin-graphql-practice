package com.lexterlab.fusionfallapi.fusionfall.controller

import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonInput
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonOutput
import com.lexterlab.fusionfallapi.fusionfall.utils.RestRoutes.ADD_CARTOON
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CartoonController(val addCartoon: AddCartoon) {

    @PostMapping(ADD_CARTOON)
    fun addCartoon(@Valid @RequestBody input: AddCartoonInput): ResponseEntity<AddCartoonOutput> {
        return ResponseEntity(addCartoon.process(input), HttpStatus.CREATED)
    }
}