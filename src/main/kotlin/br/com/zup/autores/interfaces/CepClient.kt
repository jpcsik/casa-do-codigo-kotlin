package br.com.zup.autores.interfaces

import br.com.zup.autores.dto.EnderecoResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface CepClient {

    @Get("/{cep}/json")
    fun consulta(@PathVariable("cep") cep: String) : EnderecoResponse

    /*
        Para poder consumir dados em XML é preciso adicionar
        uma dependencia para que o micronaut consiga fazer a desserialização
     */

//    @Get(value = "/{cep}/xml", consumes = [MediaType.APPLICATION_XML])
//    fun consultaXml(@PathVariable("cep") cep: String) : EnderecoResponse
}