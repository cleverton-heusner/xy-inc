package br.com.xyinc.gerador;

import br.com.xyinc.gps.Gps;

public class GeradorGps {

	private Integer x = 2;
	private Integer y = 2;
	private Double distMaxDePontos = 1d;

	public GeradorGps() {
	}

	public GeradorGps comDistMaxForaDoRaioGps() {
		this.distMaxDePontos = 0d;
		return this;
	}

	public GeradorGps comDistMaxNegativa() {
		this.distMaxDePontos = -1d;
		return this;
	}

	public GeradorGps comCoordXNegativa() {
		this.x = -1;
		return this;
	}

	public GeradorGps comCoordYNegativa() {
		this.y = -1;
		return this;
	}

	public Gps gerar() {
		return new Gps().setX(x).setY(y).setDistMaxDePontos(distMaxDePontos);
	}
}
