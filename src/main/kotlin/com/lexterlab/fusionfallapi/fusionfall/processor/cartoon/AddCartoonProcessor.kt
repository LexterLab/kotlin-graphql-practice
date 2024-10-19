package com.lexterlab.fusionfallapi.fusionfall.processor.cartoon

import com.lexterlab.fusionfallapi.fusionfall.model.Cartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonInput
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonOutput
import com.lexterlab.fusionfallapi.fusionfall.repository.CartoonRepository
import org.springframework.stereotype.Service

@Service
class AddCartoonProcessor(val cartoonRepository: CartoonRepository): AddCartoon {

    override fun process(input: AddCartoonInput): AddCartoonOutput {
        val cartoon = Cartoon(name = input.name)
        val output = AddCartoonOutput(id = cartoonRepository.save(cartoon).id)
        return output
    }
}