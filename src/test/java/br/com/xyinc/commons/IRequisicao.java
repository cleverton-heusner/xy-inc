package br.com.xyinc.commons;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;
import io.restassured.response.Response;

public interface IRequisicao {

	public Response requisitar(final Ponto ponto) throws URISyntaxException;
}