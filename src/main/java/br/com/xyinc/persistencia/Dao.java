package br.com.xyinc.persistencia;

import static java.util.Collections.emptyList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.xyinc.persistencia.excecao.NaoEncontradoExcecao;

public class Dao implements IDao {

	@Inject
	private EntityManagerHelper helper;

	@Override
	public <E> List<E> listar(Class<E> clazz) throws NaoEncontradoExcecao {
		EntityManager em = helper.entityManager();
		List<E> entities = emptyList();

		try {
			helper.iniciarTransacao();
			CriteriaQuery<E> cq = em.getCriteriaBuilder().createQuery(clazz);
			entities = em.createQuery(cq.select(cq.from(clazz))).getResultList();
			helper.concluirTransacao();

			if (entities.isEmpty()) {
				throw new NaoEncontradoExcecao();
			}
		} finally {
			helper.close();
		}

		return entities;
	}
}
