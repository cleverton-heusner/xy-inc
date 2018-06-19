package br.com.xyinc.ponto.proximidade.teste;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.xyinc.commons.TesteBase;
import br.com.xyinc.massa_dados.IMassaDados;
import br.com.xyinc.massa_dados.MassaDadosProximidadeQualifier;
import br.com.xyinc.persistencia.excecao.PersistenciaExcecao;
import br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComCoordXNegativasGps;
import br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComCoordYNegativasGps;
import br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComDistMaxForaDoRaioGps;
import br.com.xyinc.ponto.proximidade.cenario.CenarioPtosComDistMaxNegativa;
import br.com.xyinc.ponto.proximidade.cenario.CenarioPtosMaisProximosGps;

@FixMethodOrder(NAME_ASCENDING)
public class PontosMaisProximosTeste extends TesteBase {

	@Inject
	private CenarioPtosMaisProximosGps cenarioPtosMaisProximosGps;
	@Inject
	private CenarioPtosComDistMaxForaDoRaioGps cenarioPtosForaDoRaioGps;
	@Inject
	private CenarioPtosComCoordXNegativasGps cenarioPtosComCoordXNegativasGps;
	@Inject
	private CenarioPtosComCoordYNegativasGps cenarioPtosComCoordYNegativasGps;
	@Inject
	private CenarioPtosComDistMaxNegativa cenarioPtosComDistMaxNegativa;
	@Inject
	@MassaDadosProximidadeQualifier
	private IMassaDados massaDados;

	@Test
	public void _00_criarMassaDados() throws PersistenciaExcecao {
		massaDados.criar();
	}

	@Test
	public void _01_testarPontosMaisProximosGps() throws Exception {
		resposta = cenarioPtosMaisProximosGps.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_OK));
		assertThat(tipoConteudo(), equalTo(JSON.toString()));
		assertThat(nomesPtosMaisProxDeGps(), is(not(empty())));
	}

	private List<String> nomesPtosMaisProxDeGps() {
		return resposta.body().jsonPath().getList("", String.class);
	}

	@Test
	public void _02_testarPontosForaDoRaioGps() throws Exception {
		resposta = cenarioPtosForaDoRaioGps.criar().resposta();
		int distMax = cenarioPtosForaDoRaioGps.distMax();

		assertThat(codigoStatus(), equalTo(SC_NOT_FOUND));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo(format("Nenhum ponto de interesse no raio de %s metro(s).", distMax)));
	}

	@Test
	public void _03_testarPontosComCoordXNegativaGps() throws Exception {
		resposta = cenarioPtosComCoordXNegativasGps.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("As coordenadas devem ter valores positivos."));
	}

	@Test
	public void _04_testarPontosComCoordYNegativaGps() throws Exception {
		resposta = cenarioPtosComCoordYNegativasGps.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("As coordenadas devem ter valores positivos."));
	}

	@Test
	public void _05_testarPontosComDistMaxNegativa() throws Exception {
		resposta = cenarioPtosComDistMaxNegativa.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("A distancia maxima deve ser maior que zero."));
	}

	@Test
	public void _06_removerMassaDados() {
		massaDados.remover();
	}
}