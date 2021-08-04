package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(@Inject val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {

        val autor: Autor = request.paraAutor()
        autorRepository.save(autor)

        println("Nome: ${autor.nome},  Email: ${autor.email}, Descricao: ${autor.descricao}")

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

}