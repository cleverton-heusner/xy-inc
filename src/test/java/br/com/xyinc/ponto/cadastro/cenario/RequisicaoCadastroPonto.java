package br.com.xyinc.ponto.cadastro.cenario;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;
import io.restassured.response.Response;

public class RequisicaoCadastroPonto {

	public Response requisitar(final Ponto ponto) throws URISyntaxException {
		return given().accept(TEXT).contentType(JSON).body(ponto).post(new URI("/ponto")).andReturn();
	}
}
