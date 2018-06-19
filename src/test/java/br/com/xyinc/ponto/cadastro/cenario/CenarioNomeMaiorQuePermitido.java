package br.com.xyinc.ponto.cadastro.cenario;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;

public class CenarioNomeMaiorQuePermitido extends CenarioCadastroBase {

	@Override
	public CenarioNomeMaiorQuePermitido criar() throws URISyntaxException {
		Ponto ponto = geradorPonto.comNomeMaiorQuePermitido().gerar();
		resposta = reqCadastroPonto.requisitar(ponto);
		
		return this;
	}
}
