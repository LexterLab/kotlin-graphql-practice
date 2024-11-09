package com.lexterlab.fusionfallapi.fusionfall.processor.cartoon

import arrow.core.Either
import arrow.core.flatMap
import com.lexterlab.fusionfallapi.fusionfall.aspect.inputvalidation.ValidateInput
import com.lexterlab.fusionfallapi.fusionfall.error.ErrorHandler
import com.lexterlab.fusionfallapi.fusionfall.error.ErrorWrapper
import com.lexterlab.fusionfallapi.fusionfall.model.Cartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoon
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonInput
import com.lexterlab.fusionfallapi.fusionfall.operations.addcartoon.AddCartoonOutput
import com.lexterlab.fusionfallapi.fusionfall.repository.CartoonRepository
import org.springframework.stereotype.Service

@Service
class AddCartoonProcessor(val cartoonRepository: CartoonRepository, val errorHandler: ErrorHandler): AddCartoon {

    @ValidateInput
    override fun process(input: AddCartoonInput): Either<ErrorWrapper, AddCartoonOutput> {
        return buildCartoon(input)
            .flatMap {
                saveCartoon(it)
            }
    }

    private fun buildCartoon(input: AddCartoonInput): Either<ErrorWrapper, Cartoon> {
        return Either.catch {
            Cartoon(
                name = input.name
            )
        }.mapLeft { errorHandler.handle(it) }
    }

    private fun saveCartoon(cartoon: Cartoon): Either<ErrorWrapper, AddCartoonOutput> {
        return Either.catch {
            val savedCartoon = cartoonRepository.save(cartoon)
            AddCartoonOutput(
                id = savedCartoon.id
            )
        }.mapLeft { errorHandler.handle(it) }
    }
}