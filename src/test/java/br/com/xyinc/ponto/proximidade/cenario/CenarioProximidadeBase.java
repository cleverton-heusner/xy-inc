package br.com.xyinc.ponto.proximidade.cenario;

import javax.inject.Inject;

import br.com.xyinc.commons.CenarioBase;
import br.com.xyinc.gerador.GeradorGps;
import br.com.xyinc.gerador.GeradorPonto;

public abstract class CenarioProximidadeBase extends CenarioBase {

	@Inject
	protected GeradorPonto geradorPonto;
	@Inject
	protected GeradorGps geradorGps;
	@Inject
	protected RequisicaoPontosMaisProximos reqPontosMaisProximos;
}
