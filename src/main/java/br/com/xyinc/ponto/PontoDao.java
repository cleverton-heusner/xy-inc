package br.com.xyinc.ponto;

import static java.util.Collections.emptyList;
import static java.lang.String.format;
import static java.lang.Math.round;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.xyinc.gps.Gps;
import br.com.xyinc.persistencia.Dao;
import br.com.xyinc.persistencia.EntityManagerHelper;
import br.com.xyinc.persistencia.excecao.EntidadeExistenteExcecao;
import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;

public class PontoDao extends Dao implements IPontoDao {

	private final String PARAM_COORD_X = "x";
	private final String PARAM_COORD_Y = "y";
	private final String PARAM_MAX_DISTANCIA = "max_d";
	private final String PARAM_NOME = "nome";
	private final String BUSCA_PONTO_MAIS_PROXIMO = "buscarPontoMaisProximo";
	private final String BUSCA_PONTO = "buscarPonto";
	private final String REMOCAO_POR_NOME = "deletarPorNome";

	@Inject
	private EntityManagerHelper helper;

	@Override
	public List<String> buscarPontosMaisProximoDeGps(final Gps gps) throws NaoEncontradoExcecao {
		EntityManager em = helper.entityManager();
		List<String> pontos = emptyList();

		try {
			helper.iniciarTransacao();
			TypedQuery<String> query = em.createNamedQuery(BUSCA_PONTO_MAIS_PROXIMO, String.class);
			pontos = query.setParameter(PARAM_MAX_DISTANCIA, gps.getDistMaxDePontos())
					.setParameter(PARAM_COORD_X, gps.getX()).setParameter(PARAM_COORD_Y, gps.getY()).getResultList();
			helper.concluirTransacao();

			if (pontos.isEmpty()) {
				long distMax = round(gps.getDistMaxDePontos());
				throw new NaoEncontradoExcecao(format("Nenhum ponto de interesse no raio de %s metro(s).", distMax));
			}
		} finally {
			helper.close();
		}

		return pontos;
	}

	@Override
	public void criarSeNaoExistir(final Ponto ponto) throws EntidadeExistenteExcecao {
		EntityManager em = helper.entityManager();

		try {
			helper.iniciarTransacao();
			String nomePonto = buscarPonto(ponto, em);

			if (nomePonto != null) {
				helper.concluirTransacao();
				throw new EntidadeExistenteExcecao("Ponto de interesse ja cadastrado.");
			}
		} catch (NoResultException e) {
			em.persist(ponto);	
			em.flush();
			helper.concluirTransacao();
		} finally {
			helper.close();
		}
	}

	private String buscarPonto(final Ponto ponto, final EntityManager em) throws NoResultException {
		Query query = em.createNamedQuery(BUSCA_PONTO);
		return (String) query.setParameter(PARAM_NOME, ponto.getNome()).setParameter(PARAM_COORD_X, ponto.getX())
				.setParameter(PARAM_COORD_Y, ponto.getY()).getSingleResult();
	}

	@Override
	public int deletarPorNome(final String nome) {
		EntityManager em = helper.entityManager();
		int pontosDeletados = 0;

		try {
			helper.iniciarTransacao();
			pontosDeletados = em.createNamedQuery(REMOCAO_POR_NOME).setParameter("nome", nome).executeUpdate();
			helper.concluirTransacao();

			return pontosDeletados;
		} finally {
			helper.close();
		}
	}
}