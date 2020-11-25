package com.marcoscoutozup.address

import com.marcoscoutozup.exception.StandardException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import javax.inject.Inject

@Controller("/address")
class AddressController {

    @Inject
    lateinit var addressClient: AddressClient

    @Get("/{cep}")
    fun getAddress(@PathVariable cep: String): HttpResponse<*> {
        val address = addressClient.getAddress(cep)

        if(address.status != HttpStatus.OK) {
            val response = StandardException(HttpStatus.NOT_FOUND.code, setOf("Cep não encontrado"))
            return HttpResponse.notFound(response)
        }

        return HttpResponse.ok(address.body())
    }

}