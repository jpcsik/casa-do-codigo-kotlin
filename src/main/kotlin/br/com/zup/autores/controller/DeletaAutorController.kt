package br.com.zup.autores.controller

import br.com.zup.autores.interfaces.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.inject.Inject
import javax.transaction.Transactional

@Controller("/autores")
class DeletaAutorController(@Inject val autorRepository: AutorRepository) {

    @Delete("/{id}")
    @Transactional
    fun deleta(@PathVariable id: Long): HttpResponse<Any> {

        val possivelAutor = autorRepository.findById(id)

        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        autorRepository.deleteById(id)
        return HttpResponse.ok()

    }

}