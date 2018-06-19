package br.com.xyinc.ponto.cadastro.cenario;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;

public class CenarioCoordenadaXNegativa extends CenarioCadastroBase {

	@Override
	public CenarioCoordenadaXNegativa criar() throws URISyntaxException {
		Ponto ponto = geradorPonto.comCoordXNegativa().gerar();
		resposta = reqCadastroPonto.requisitar(ponto);
		
		return this;
	}
}
