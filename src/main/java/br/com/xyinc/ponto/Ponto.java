package br.com.xyinc.ponto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Cleve
 *
 */
@Entity
@Table(name = "ponto", uniqueConstraints = { @UniqueConstraint(columnNames = { "x", "y" }) })
@NamedQueries({
		@NamedQuery(name = "buscarPonto", query = "SELECT p.nome FROM Ponto p WHERE "
				+ "p.nome = :nome OR p.x = :x AND p.y = :y"),
		@NamedQuery(name = "buscarPontoMaisProximo", query = "SELECT p.nome FROM Ponto p "
				+ "WHERE FLOOR(SQRT(POWER(p.x - :x, 2) + POWER(p.y - :y, 2))) <= :max_d"),
		@NamedQuery(name = "deletarPorNome", query = "DELETE FROM Ponto p WHERE p.nome = :nome") })
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50, unique = true)
	@NotNull(message = "{ponto.nome.notnull}")
	@NotEmpty(message = "{ponto.nome.notnull}")
	@Size(max = 50, message = "{ponto.nome.size}")
	private String nome;

	@NotNull(message = "{coord.notnull}")
	@Min(value = 0, message = "{coord.min}")
	private Integer x;

	@NotNull(message = "{coord.notnull}")
	@Min(value = 0, message = "{coord.min}")
	private Integer y;

	public Ponto() {
	}

	public Ponto(String nome, Integer x, Integer y) {
		this.nome = nome;
		this.x = x;
		this.y = y;
	}

	public long getId() {
		return id;
	}

	public Ponto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Ponto setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public Integer getX() {
		return x;
	}

	public Ponto setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public Ponto setY(Integer y) {
		this.y = y;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ponto [nome=");
		builder.append(nome);
		builder.append(", x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}
