package com.marcoscoutozup.address

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import io.micronaut.retry.annotation.CircuitBreaker
import io.micronaut.retry.annotation.Fallback
import io.micronaut.retry.annotation.Retryable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.validation.constraints.Digits

@Client("\${host.viacep}", errorType = AddressClient.AddressFallBack::class)
@Retryable(attempts = "1", delay = "500ms")
interface AddressClient {

    @Get("/ws/{cep}/json/")
    fun getAddress(@PathVariable cep: String): HttpResponse<Address>

    @Fallback
    class AddressFallBack: AddressClient {
        override fun getAddress(cep: String): HttpResponse<Address> {
            return HttpResponse.notFound()
        }
    }

}
