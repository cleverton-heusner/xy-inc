package br.com.xyinc.gerador;

import org.apache.commons.lang3.StringUtils;

import br.com.xyinc.ponto.Ponto;

public class GeradorPonto {

	private static final int MAX_NUM_CARACTERES_NOME = 50;
	private String nome = "PontoDeInteresse";
	private Integer x = 1;
	private Integer y = 1;

	public GeradorPonto() {
	}

	public GeradorPonto comCoordX(final Integer x) {
		this.x = x;
		return this;
	}

	public GeradorPonto comCoordY(final Integer y) {
		this.x = y;
		return this;
	}

	public GeradorPonto comCoordXNegativa() {
		this.x = -1;
		return this;
	}

	public GeradorPonto comCoordYNegativa() {
		this.y = -1;
		return this;
	}

	public GeradorPonto comNome(final String nome) {
		this.nome = nome;
		return this;
	}

	public GeradorPonto comNomeVazio() {
		this.nome = "";
		return this;
	}

	public GeradorPonto comNomeMaiorQuePermitido() {
		this.nome = StringUtils.repeat(' ', MAX_NUM_CARACTERES_NOME + 1);
		return this;
	}

	public Ponto gerar() {
		return new Ponto(nome, x, y);
	}
}
