package br.com.xyinc.persistencia;

import java.util.List;

import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;

public interface IDao {
	public <E> List<E> listar(Class<E> clazz) throws NaoEncontradoExcecao;
}
