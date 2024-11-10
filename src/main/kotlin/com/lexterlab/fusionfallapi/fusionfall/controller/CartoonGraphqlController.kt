package com.lexterlab.fusionfallapi.fusionfall.controller

import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonInput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CartoonGraphqlController(val addCartoon: AddCartoon): BaseController() {

    @MutationMapping
    fun createCartoon(@Argument input: AddCartoonInput): Any {
        return handleOutput(addCartoon.process(input))
    }
}