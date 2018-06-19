package br.com.xyinc.massa_dados;

import static br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComDistMaxForaDoRaioGps.COORD_X_PONTO_DIST_MAX_FORA_RAIO_GPS;
import static br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComDistMaxForaDoRaioGps.COORD_Y_PONTO_DIST_MAX_FORA_RAIO_GPS;
import static br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComDistMaxForaDoRaioGps.NOME_PONTO_DIST_MAX_FORA_RAIO_GPS;
import static br.com.xyinc.ponto.proximidade.cenario.CenarioPtosMaisProximosGps.NOME_PONTO_MAIS_PROX_GPS;

import javax.inject.Inject;

import br.com.xyinc.gerador.GeradorPonto;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.PersistenciaExcecao;
import br.com.xyinc.ponto.IPontoDao;
import br.com.xyinc.ponto.Ponto;

@MassaDadosProximidadeQualifier
public class MassaDadosProximidade implements IMassaDados {

	@Inject
	private GeradorPonto geradorPonto;

	@Inject
	private IPontoDao dao;

	@Override
	public void criar() throws PersistenciaExcecao {
		criarPontoMaisProximoDeGps();
		criarPontoComDistMaxForaRaioGps();
	}

	private void criarPontoMaisProximoDeGps() throws EntidadeExistenteExcecao {
		Ponto ptoMaisProxGps = geradorPonto.comNome(NOME_PONTO_MAIS_PROX_GPS).gerar();
		dao.criarSeNaoExistir(ptoMaisProxGps);
	}

	private void criarPontoComDistMaxForaRaioGps() throws EntidadeExistenteExcecao {
		Ponto ptoDistMaxForaRaioGps = geradorPonto.comNome(NOME_PONTO_DIST_MAX_FORA_RAIO_GPS)
				.comCoordX(COORD_X_PONTO_DIST_MAX_FORA_RAIO_GPS).comCoordY(COORD_Y_PONTO_DIST_MAX_FORA_RAIO_GPS)
				.gerar();
		dao.criarSeNaoExistir(ptoDistMaxForaRaioGps);
	}

	@Override
	public void remover() {
		dao.deletarPorNome(NOME_PONTO_MAIS_PROX_GPS);
		dao.deletarPorNome(NOME_PONTO_DIST_MAX_FORA_RAIO_GPS);
	}
}
