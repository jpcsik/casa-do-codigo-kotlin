package br.com.zup.autores.dto

import br.com.zup.autores.Autor

class DetalheAutoresResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
