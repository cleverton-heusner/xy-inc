package br.com.xyinc.ponto.cadastro.cenario;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;

public class CenarioCoordenadaYNegativa extends CenarioCadastroBase {

	@Override
	public CenarioCoordenadaYNegativa criar() throws URISyntaxException {
		Ponto ponto = geradorPonto.comCoordYNegativa().gerar();
		resposta = reqCadastroPonto.requisitar(ponto);
		
		return this;
	}
}
