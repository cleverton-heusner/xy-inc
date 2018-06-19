package br.com.xyinc.ponto.cadastro.cenario;

import br.com.xyinc.ponto.Ponto;

public class CenarioDuplicidade extends CenarioCadastroBase {

	public static final String NOME_PONTO_JA_EXISTENTE = "Ponto já existente";
	public static final int COORD_X_PONTO_JA_EXISTENTE = 2;
	public static final int COORD_Y_PONTO_JA_EXISTENTE = 2;

	@Override
	public CenarioDuplicidade criar() throws Exception {
		Ponto ponto = geradorPonto.comNome(NOME_PONTO_JA_EXISTENTE).comCoordX(COORD_X_PONTO_JA_EXISTENTE)
				.comCoordY(COORD_Y_PONTO_JA_EXISTENTE).gerar();
		resposta = reqCadastroPonto.requisitar(ponto);

		return this;
	}
}
