package br.com.xyinc.commons;

import io.restassured.response.Response;

public abstract class CenarioBase {

	protected Response resposta;

	public abstract CenarioBase criar() throws Exception;

	public Response resposta() {
		return resposta;
	}
}
