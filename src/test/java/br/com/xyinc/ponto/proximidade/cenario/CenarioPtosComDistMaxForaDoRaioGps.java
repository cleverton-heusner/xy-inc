package br.com.xyinc.ponto.proximidade.cenario;

import br.com.xyinc.gps.Gps;

public class CenarioPtosComDistMaxForaDoRaioGps extends CenarioProximidadeBase {

	private int distMax;
	public static final String NOME_PONTO_DIST_MAX_FORA_RAIO_GPS = "Ponto com d-maxfora do raio de GPS";
	public static final int COORD_X_PONTO_DIST_MAX_FORA_RAIO_GPS = 2;
	public static final int COORD_Y_PONTO_DIST_MAX_FORA_RAIO_GPS = 2;

	@Override
	public CenarioPtosComDistMaxForaDoRaioGps criar() throws Exception {
		Gps gps = geradorGps.comDistMaxForaDoRaioGps().gerar();
		distMax = gps.getDistMaxDePontos().intValue();
		resposta = reqPontosMaisProximos.requisitar(gps);

		return this;
	}

	public int distMax() {
		return distMax;
	}
}
