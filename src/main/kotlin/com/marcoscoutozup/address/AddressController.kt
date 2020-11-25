package com.marcoscoutozup.address

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/address")
class AddressController(val client: AddressClient) {

    @Get("/{cep}")
    fun getAddress(@PathVariable cep: String): HttpResponse<*> {
        val address = client.getAddress(cep)

        if(address.status != HttpStatus.OK){
            return HttpResponse.notFound(mapOf("message" to "Cep não encontrado"))
        }

        return HttpResponse.ok(address.body())
    }

}