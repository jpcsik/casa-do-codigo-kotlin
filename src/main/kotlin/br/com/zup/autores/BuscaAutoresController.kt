package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/autores")
class BuscaAutoresController(@Inject val autorRepository: AutorRepository) {

    @Get
    fun busca(): HttpResponse<List<DetalheAutoresResponse>> {
        val autores = autorRepository.findAll()

        val resposta = autores.map { autor -> DetalheAutoresResponse(autor) }

        return HttpResponse.ok(resposta)
    }

}