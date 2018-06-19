package br.com.xyinc.gps;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Gps {
	@NotNull(message = "{coord.notnull}")
	@Min(value = 0, message = "{coord.min}")
	private Integer x;

	@NotNull(message = "{coord.notnull}")
	@Min(value = 0, message = "{coord.min}")
	private Integer y;

	@NotNull(message = "{gps.dist_max.notnull}")
	@Min(value = 0, message = "{gps.dist_max.min}")
	private Double distMaxDePontos;

	public Integer getX() {
		return x;
	}

	public Gps setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public Gps setY(Integer y) {
		this.y = y;
		return this;
	}

	public Double getDistMaxDePontos() {
		return distMaxDePontos;
	}

	public Gps setDistMaxDePontos(Double distMaxDePontos) {
		this.distMaxDePontos = distMaxDePontos;
		return this;
	}
}
