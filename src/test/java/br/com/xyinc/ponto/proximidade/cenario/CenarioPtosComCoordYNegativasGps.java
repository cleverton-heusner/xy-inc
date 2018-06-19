package br.com.xyinc.ponto.proximidade.cenario;

import br.com.xyinc.gps.Gps;

public class CenarioPtosComCoordYNegativasGps extends CenarioProximidadeGps {

	@Override
	public CenarioPtosComCoordYNegativasGps criar() throws Exception {
		Gps gps = geradorGps.comCoordYNegativa().gerar();
		resposta = reqPontosMaisProximos.requisitar(gps);

		return this;
	}
}
