package br.com.xyinc.massa_dados;

import static br.com.xyinc.ponto.cadastro.cenario.CenarioDuplicidade.COORD_X_PONTO_JA_EXISTENTE;
import static br.com.xyinc.ponto.cadastro.cenario.CenarioDuplicidade.COORD_Y_PONTO_JA_EXISTENTE;
import static br.com.xyinc.ponto.cadastro.cenario.CenarioDuplicidade.NOME_PONTO_JA_EXISTENTE;

import javax.inject.Inject;

import br.com.xyinc.gerador.GeradorPonto;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.ponto.IPontoDao;
import br.com.xyinc.ponto.Ponto;
import br.com.xyinc.ponto.cadastro.cenario.CenarioCadastroValido;

@MassaDadosCadastroPontoQualifier
public class MassaDadosCadastroPonto implements IMassaDados {

	@Inject
	private GeradorPonto geradorPonto;

	@Inject
	private IPontoDao dao;

	@Override
	public void criar() throws EntidadeExistenteExcecao {
		Ponto ponto = geradorPonto.comNome(NOME_PONTO_JA_EXISTENTE).comCoordX(COORD_X_PONTO_JA_EXISTENTE)
				.comCoordY(COORD_Y_PONTO_JA_EXISTENTE).gerar();
		dao.criarSeNaoExistir(ponto);
	}

	@Override
	public void remover() {
		dao.deletarPorNome(CenarioCadastroValido.NOME_PONTO_CADASTRO_VALIDO);
		dao.deletarPorNome(NOME_PONTO_JA_EXISTENTE);
	}
}
