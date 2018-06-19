package br.com.xyinc.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerHelper {
	private EntityManagerFactory factory;
	private EntityManager em;

	public EntityManager entityManager() {
		factory = Persistence.createEntityManagerFactory("xy-inc");
		em = factory.createEntityManager();
		return em;
	}
	
	public void iniciarTransacao() {
		em.getTransaction().begin();
	}
	
	public void concluirTransacao() {
		em.getTransaction().commit();
	}

	public void close() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}

		if (em != null && em.isOpen()) {
			em.close();
		}
	}
}