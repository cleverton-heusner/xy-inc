package br.com.xyinc.persistencia.excecao;

public class PersistenciaExcecao extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenciaExcecao(String msg) {
		super(msg);
	}

	public PersistenciaExcecao() {
	}
}
