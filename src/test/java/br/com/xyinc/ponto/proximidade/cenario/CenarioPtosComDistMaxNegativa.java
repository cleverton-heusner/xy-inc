package br.com.xyinc.ponto.proximidade.cenario;

import br.com.xyinc.gps.Gps;

public class CenarioPtosComDistMaxNegativa extends CenarioProximidadeGps {

	@Override
	public CenarioPtosComDistMaxNegativa criar() throws Exception {
		Gps gps = geradorGps.comDistMaxNegativa().gerar();
		resposta = reqPontosMaisProximos.requisitar(gps);

		return this;
	}
}
