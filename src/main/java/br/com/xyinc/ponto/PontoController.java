package br.com.xyinc.ponto;

import java.util.List;

import javax.inject.Inject;

import br.com.xyinc.gps.Gps;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;
import br.com.xyinc.validacao.ValidacaoExcecao;
import br.com.xyinc.validacao.ValidadorEntidade;

public class PontoController {

	@Inject
	private IPontoDao dao;
	@Inject
	private ValidadorEntidade validator;

	public List<Ponto> listarPontos() throws NaoEncontradoExcecao {
		return dao.listar(Ponto.class);
	}

	public List<String> buscarPontosMaisProximosDeGps(final Gps gps) throws ValidacaoExcecao, NaoEncontradoExcecao {
		validator.validate(gps);
		return dao.buscarPontosMaisProximoDeGps(gps);
	}

	public void criarPontoSeNaoExistir(final Ponto ponto) throws ValidacaoExcecao, EntidadeExistenteExcecao {
		validator.validate(ponto);
		dao.criarSeNaoExistir(ponto);
	}
}