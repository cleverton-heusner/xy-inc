package br.com.xyinc.ponto.proximidade.cenario;

import br.com.xyinc.gps.Gps;

public class CenarioPtosMaisProximosGps extends CenarioProximidadeBase {

	public static final String NOME_PONTO_MAIS_PROX_GPS = "Ponto mais próximo do GPS";

	@Override
	public CenarioPtosMaisProximosGps criar() throws Exception {
		Gps gps = geradorGps.gerar();
		resposta = reqPontosMaisProximos.requisitar(gps);

		return this;
	}
}
