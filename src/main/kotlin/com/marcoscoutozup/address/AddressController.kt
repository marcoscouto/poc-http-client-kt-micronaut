package com.marcoscoutozup.address

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import io.opentracing.Tracer

@Controller("/address")
open class AddressController(val client: AddressClient, val tracer: Tracer) {

    @ContinueSpan
    @Get("/{cep}")
    open fun getAddress(@SpanTag("cep") @PathVariable cep: String): HttpResponse<*> {
        val address = client.getAddress(cep)

        if(address.status != HttpStatus.OK){
            tracer.activeSpan().log("Address not found ")
            return HttpResponse.notFound(mapOf("message" to "Cep não encontrado"))
        }

        return HttpResponse.ok(address.body())
    }

}