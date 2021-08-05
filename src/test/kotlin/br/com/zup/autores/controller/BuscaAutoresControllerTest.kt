package br.com.zup.autores.controller

import br.com.zup.autores.dto.DetalheAutoresResponse
import br.com.zup.autores.dto.EnderecoResponse
import br.com.zup.autores.interfaces.AutorRepository
import br.com.zup.autores.modelo.Autor
import br.com.zup.autores.modelo.Endereco
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    private lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        val enderecoResponse = EnderecoResponse(
            logradouro = "Rua Avenida",
            bairro = "Bairro",
            localidade = "Cidade",
            uf = "UF"
        )

        val endereco = Endereco(enderecoResponse)

        autor = Autor(
            nome = "Joao Pedro",
            email = "jp@email.com",
            descricao = "qualquer coisa",
            endereco
        )

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`() {

        val response = client
            .toBlocking()
            .exchange("/autores?email=${autor.email}", DetalheAutoresResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }


}