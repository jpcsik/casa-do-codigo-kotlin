package br.com.zup.autores.controller

import br.com.zup.autores.AutorRepository
import br.com.zup.autores.dto.DetalheAutoresResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.inject.Inject

@Controller("/autores")
class BuscaAutoresController(@Inject val autorRepository: AutorRepository) {

    @Get
    fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

        if (email.isBlank()) {
            val autores = autorRepository.findAll()

            val resposta = autores.map { autor -> DetalheAutoresResponse(autor) }

            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.findByEmail(email)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        return HttpResponse.ok(DetalheAutoresResponse(autor))
    }

}