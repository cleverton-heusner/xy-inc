package br.com.xyinc.ponto.proximidade.cenario;

import br.com.xyinc.gps.Gps;

public class CenarioPtosComCoordXNegativasGps extends CenarioProximidadeGps {

	@Override
	public CenarioPtosComCoordXNegativasGps criar() throws Exception {
		Gps gps = geradorGps.comCoordXNegativa().gerar();
		resposta = reqPontosMaisProximos.requisitar(gps);
		return this;
	}
}
