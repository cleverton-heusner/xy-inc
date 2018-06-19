package br.com.xyinc.ponto;

import static br.com.xyinc.constantes.HttpStatus.BAD_REQUEST;
import static br.com.xyinc.constantes.HttpStatus.CONFLICT;
import static br.com.xyinc.constantes.HttpStatus.NOT_FOUND;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import br.com.xyinc.gps.Gps;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;

import static br.com.xyinc.utils.FiltroCors.habilitarCors;
import br.com.xyinc.utils.JsonMapper;
import br.com.xyinc.utils.JsonTransformer;
import br.com.xyinc.validacao.ValidacaoExcecao;
import spark.Request;

public class PontoServico {

	@Inject
	private PontoController pontoController;

	public void inicializar(@Observes ContainerInitialized event) {

		habilitarCors();
		
		get("/ponto", (req, res) -> {
			res.type("application/json");

			return pontoController.listarPontos();
		}, new JsonTransformer());

		get("/ponto/mais-proximos", (req, res) -> {
			res.type("application/json");

			Gps gps = new Gps();
			gps.setX(req.queryMap("x").integerValue()).setY(req.queryMap("y").integerValue())
					.setDistMaxDePontos(req.queryMap("d-max").doubleValue());
			return pontoController.buscarPontosMaisProximosDeGps(gps);
		}, new JsonTransformer());

		post("/ponto", (req, res) -> {
			res.type("text/plain");

			Ponto ponto = new JsonMapper().fromJSON(req.body(), Ponto.class);
			pontoController.criarPontoSeNaoExistir(ponto);
			return "Ponto de interesse cadastrado com sucesso.";
		});

		exception(ValidacaoExcecao.class, (exception, req, res) -> {
			res.body(exception.getMessage());
			res.status(BAD_REQUEST);
			res.type("text/plain");
		});

		exception(EntidadeExistenteExcecao.class, (exception, req, res) -> {
			res.body(exception.getMessage());
			res.status(CONFLICT);
			res.type("text/plain");
		});

		exception(NaoEncontradoExcecao.class, (exception, req, res) -> {
			res.body(mensagemPontoNaoEncontrado(exception, req));
			res.status(NOT_FOUND);
			res.type("text/plain");
		});
	}

	private static String mensagemPontoNaoEncontrado(Exception exception, Request request) {
		if ("/ponto".equals(request.uri())) {
			return "Nenum ponto de interesse encontrado.";
		}

		return exception.getMessage();
	}
}
