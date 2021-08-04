package br.com.zup.autores.controller

import br.com.zup.autores.AutorRepository
import br.com.zup.autores.dto.DetalheAutoresResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.inject.Inject

@Controller("/autores")
class AtualizaAutorController(@Inject val autorRepository: AutorRepository) {

    @Put("/{id}")
    fun atualiza(@PathVariable id: Long, descricao: String) : HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()

        autor.descricao = descricao

        autorRepository.update(autor)
        return HttpResponse.ok(DetalheAutoresResponse(autor))
    }

}