package br.com.xyinc.ponto.cadastro.cenario;

import javax.inject.Inject;

import br.com.xyinc.commons.CenarioBase;
import br.com.xyinc.gerador.GeradorPonto;

public abstract class CenarioCadastroBase extends CenarioBase {
	
	@Inject
	protected GeradorPonto geradorPonto;
	@Inject
	protected RequisicaoCadastroPonto reqCadastroPonto;
}
