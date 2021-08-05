package br.com.zup.autores

import br.com.zup.autores.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface CepClient {

    @Get("/{cep}/json")
    fun consulta(@PathVariable("cep") cep: String) : EnderecoResponse

}