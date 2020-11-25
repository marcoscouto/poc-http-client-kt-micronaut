package com.marcoscoutozup.address

data class Address (
        val cep: String,
        val logradouro: String,
        val complemento: String,
        val bairro: String,
        val localidade: String,
        val uf: String
)