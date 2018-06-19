package br.com.xyinc.commons;

import static io.restassured.RestAssured.port;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.com.xyinc.cdi.WeldJUnit4Runner;
import io.restassured.response.Response;
import spark.Spark;

@RunWith(WeldJUnit4Runner.class)
public abstract class TesteBase {

	private static final int SERVER_PORT = 4567;
	protected Response resposta;

	@BeforeClass
	public static void setup() {
		port = SERVER_PORT;
	}

	@AfterClass
	public static void teardown() {
		Spark.stop();
	}

	protected int codigoStatus() {
		return resposta.statusCode();
	}

	protected String tipoConteudo() {
		return resposta.contentType();
	}

	protected String mensagem() {
		return resposta.body().asString();
	}
}
