package br.com.xyinc.ponto;

import java.util.List;

import br.com.xyinc.gps.Gps;
import br.com.xyinc.persistencia.IDao;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;

public interface IPontoDao extends IDao {
	public List<String> buscarPontosMaisProximoDeGps(final Gps gps) throws NaoEncontradoExcecao;

	public void criarSeNaoExistir(final Ponto ponto) throws EntidadeExistenteExcecao;

	public int deletarPorNome(final String nome);
}
