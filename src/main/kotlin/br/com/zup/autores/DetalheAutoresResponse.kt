package br.com.zup.autores

class DetalheAutoresResponse(autor: Autor) {

    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
}
