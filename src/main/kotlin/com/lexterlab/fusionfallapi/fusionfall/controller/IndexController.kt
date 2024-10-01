package com.lexterlab.fusionfallapi.fusionfall.controller

import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonInput
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @PostMapping("/index")
    fun index(@RequestBody input: AddCartoonInput): ResponseEntity<AddCartoonOutput> {
        val output = AddCartoonOutput()
        return ResponseEntity(output, HttpStatus.CREATED)
    }
}