package br.com.xyinc.ponto.proximidade.cenario;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.xyinc.gps.Gps;
import io.restassured.response.Response;

public class RequisicaoPontosMaisProximos {

	public Response requisitar(final Gps gps) throws URISyntaxException {
		return given().accept(JSON).queryParam("x", String.valueOf(gps.getX()))
				.queryParam("y", String.valueOf(gps.getY()))
				.queryParam("d-max", String.valueOf(gps.getDistMaxDePontos())).get(new URI("/ponto/mais-proximos"))
				.andReturn();
	}
}
