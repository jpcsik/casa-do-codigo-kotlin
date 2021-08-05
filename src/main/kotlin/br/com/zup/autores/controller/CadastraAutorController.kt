package br.com.zup.autores.controller

import br.com.zup.autores.modelo.Autor
import br.com.zup.autores.interfaces.AutorRepository
import br.com.zup.autores.interfaces.CepClient
import br.com.zup.autores.dto.NovoAutorRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(
    @Inject val autorRepository: AutorRepository,
    @Inject val cepClient: CepClient
) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {

        val enderecoResponse = cepClient.consulta(request.cep)

        val autor: Autor = request.paraAutor(enderecoResponse)
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

}