package br.com.xyinc.persistencia.excecao;

public class NaoEncontradoExcecao extends PersistenciaExcecao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoEncontradoExcecao() {
		super();
	}
	
	public NaoEncontradoExcecao(final String msg) {
		super(msg);
	}
}
