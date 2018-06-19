package br.com.xyinc.ponto.proximidade.cenario;

import javax.inject.Inject;

import br.com.xyinc.commons.CenarioBase;
import br.com.xyinc.gerador.GeradorGps;

public abstract class CenarioProximidadeGps extends CenarioBase {

	@Inject
	protected GeradorGps geradorGps;
	@Inject
	protected RequisicaoPontosMaisProximos reqPontosMaisProximos;
}
