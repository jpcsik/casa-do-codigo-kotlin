package br.com.zup.autores.dto

import br.com.zup.autores.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected //cria um bean de introspecção, para conseguir acessar os atributos da classe
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
) {
    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}
