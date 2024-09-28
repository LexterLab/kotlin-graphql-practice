package com.lexterlab.fusionfallapi.fusionfall.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @GetMapping("/index")
    fun index(@RequestParam name: String): ResponseEntity<String> {
        return ResponseEntity.ok("Hello $name")
    }
}