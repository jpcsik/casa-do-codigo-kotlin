package br.com.zup.autores.dto

import br.com.zup.autores.modelo.Autor

data class DetalheAutoresResponse(val autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
