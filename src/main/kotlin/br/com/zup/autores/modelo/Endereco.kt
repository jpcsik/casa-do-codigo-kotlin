package br.com.zup.autores.modelo

import br.com.zup.autores.dto.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable //Dados da tabela Autor
class Endereco(enderecoResponse: EnderecoResponse) {

    val logradouro = enderecoResponse.logradouro
    val localidade = enderecoResponse.localidade
    val bairro = enderecoResponse.bairro
    val uf = enderecoResponse.uf

}
