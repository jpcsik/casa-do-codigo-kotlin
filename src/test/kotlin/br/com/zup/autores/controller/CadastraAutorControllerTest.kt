package br.com.zup.autores.controller

import br.com.zup.autores.dto.EnderecoResponse
import br.com.zup.autores.dto.NovoAutorRequest
import br.com.zup.autores.interfaces.CepClient
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest {

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @field:Inject
    lateinit var cepClient: CepClient

    @Test
    fun `deve cadastrar novo autor`() {

        val novoAutorRequest = NovoAutorRequest(
            nome = "nome",
            email = "email@email.com",
            descricao = "descricao",
            "11111-222"
        )

        val enderecoResponse = EnderecoResponse(
            logradouro = "Rua Avenida",
            bairro = "Bairro",
            localidade = "Cidade",
            uf = "UF"
        )

        Mockito.`when`(cepClient.consulta(novoAutorRequest.cep)).thenReturn(enderecoResponse)

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))

    }

    @MockBean(CepClient::class)
    fun cepMock(): CepClient {
        return Mockito.mock(CepClient::class.java)
    }

}