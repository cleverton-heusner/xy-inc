package br.com.xyinc.ponto.cadastro.teste;

import static io.restassured.http.ContentType.TEXT;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.net.URISyntaxException;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;

import br.com.xyinc.commons.TesteBase;
import br.com.xyinc.massa_dados.IMassaDados;
import br.com.xyinc.massa_dados.MassaDadosCadastroPontoQualifier;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.PersistenciaExcecao;
import br.com.xyinc.ponto.cadastro.cenario.CenarioCadastroValido;
import br.com.xyinc.ponto.cadastro.cenario.CenarioCoordenadaXNegativa;
import br.com.xyinc.ponto.cadastro.cenario.CenarioCoordenadaYNegativa;
import br.com.xyinc.ponto.cadastro.cenario.CenarioDuplicidade;
import br.com.xyinc.ponto.cadastro.cenario.CenarioNomeMaiorQuePermitido;
import br.com.xyinc.ponto.cadastro.cenario.CenarioNomeVazio;

@FixMethodOrder(NAME_ASCENDING)
public class CadastroPontoTeste extends TesteBase {

	@Inject
	private CenarioCadastroValido cenarioCadastroValido;
	@Inject
	private CenarioCoordenadaXNegativa cenarioCoordXNegativa;
	@Inject
	private CenarioCoordenadaYNegativa cenarioCoordYNegativa;
	@Inject
	private CenarioNomeVazio cenarioNomeVazio;
	@Inject
	private CenarioNomeMaiorQuePermitido cenarioNomeMaiorQuePermitido;
	@Inject
	private CenarioDuplicidade cenarioDuplicidade;
	@Inject
	@MassaDadosCadastroPontoQualifier
	private IMassaDados massaDados;

	@Test
	public void _00_criarMassaDados() throws PersistenciaExcecao {
		massaDados.criar();
	}

	@Test
	public void _01_testarCadastroValido() throws URISyntaxException, EntidadeExistenteExcecao {
		resposta = cenarioCadastroValido.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_OK));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("Ponto de interesse cadastrado com sucesso."));
	}

	@Test
	public void _02_testarCoordenadaXNegativa() throws URISyntaxException {
		resposta = cenarioCoordXNegativa.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("As coordenadas devem ter valores positivos."));
	}

	@Test
	public void _03_testarCoordenadaYNegativa() throws URISyntaxException {
		resposta = cenarioCoordYNegativa.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("As coordenadas devem ter valores positivos."));
	}

	@Test
	public void _04_testarNomeVazio() throws URISyntaxException {
		resposta = cenarioNomeVazio.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("Informe um nome para o ponto de interesse."));
	}

	@Test
	public void _05_testarNomeMaiorQuePermitido() throws URISyntaxException {
		resposta = cenarioNomeMaiorQuePermitido.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_BAD_REQUEST));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("O nome do ponto de interesse deve ter menos de 50 caracteres."));
	}

	@Test
	public void _06_testarDuplicidade() throws Exception {
		resposta = cenarioDuplicidade.criar().resposta();

		assertThat(codigoStatus(), equalTo(SC_CONFLICT));
		assertThat(tipoConteudo(), equalTo(TEXT.toString()));
		assertThat(mensagem(), equalTo("Ponto de interesse ja cadastrado."));
	}

	@Test
	public void _07_removerMassaDados() {
		massaDados.remover();
	}
}
