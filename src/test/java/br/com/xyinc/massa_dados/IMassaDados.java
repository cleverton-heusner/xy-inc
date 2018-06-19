package br.com.xyinc.massa_dados;

import br.com.xyinc.persistencia.excecao.PersistenciaExcecao;

public interface IMassaDados {

	public void criar() throws PersistenciaExcecao;

	public void remover();
}
