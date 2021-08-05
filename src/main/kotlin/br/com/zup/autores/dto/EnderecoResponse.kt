package br.com.zup.autores.dto

data class EnderecoResponse(
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)
