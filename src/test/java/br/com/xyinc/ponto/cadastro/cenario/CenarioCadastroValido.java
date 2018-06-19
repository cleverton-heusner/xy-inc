package br.com.xyinc.ponto.cadastro.cenario;

import java.net.URISyntaxException;

import br.com.xyinc.ponto.Ponto;

public class CenarioCadastroValido extends CenarioCadastroBase {

	public static final String NOME_PONTO_CADASTRO_VALIDO = "Ponto com cadastro válido";

	@Override
	public CenarioCadastroValido criar() throws URISyntaxException {
		Ponto ponto = geradorPonto.comNome(NOME_PONTO_CADASTRO_VALIDO).gerar();
		resposta = reqCadastroPonto.requisitar(ponto);

		return this;
	}
}
