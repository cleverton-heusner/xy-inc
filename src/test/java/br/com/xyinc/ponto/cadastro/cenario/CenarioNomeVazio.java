package br.com.xyinc.ponto.cadastro.cenario;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;

public class CenarioNomeVazio extends CenarioCadastroBase {

	@Override
	public CenarioNomeVazio criar() throws URISyntaxException {
		Ponto ponto = geradorPonto.comNomeVazio().gerar();
		resposta = reqCadastroPonto.requisitar(ponto);
		
		return this;
	}
}
