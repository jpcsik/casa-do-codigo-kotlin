package br.com.zup.autores.dto

import br.com.zup.autores.interfaces.Cep
import br.com.zup.autores.modelo.Autor
import br.com.zup.autores.modelo.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected //cria um bean de introspecção, para conseguir acessar os atributos da classe
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank @field:Cep val cep: String,
) {
    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse)
        return Autor(nome, email, descricao, endereco)
    }
}
