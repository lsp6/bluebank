package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import Model.Conta;

@Repository("daoConta")
public class DaoConta {

	public void criar(Conta conta) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		em.close();

	}

	public List<Conta> ler(String jpql, List<String> parameterName, List parameter) {

		EntityManager em = new JPAUtil().getEm();

		Query query = em.createQuery(jpql, Conta.class);

		if (parameter != null) {
			for (int i = 0; i < parameterName.size(); i++) {
				query.setParameter(parameterName.get(i), parameter.get(i));
			}

		}
		List<Conta> contas = query.getResultList();
		em.close();
		return contas;
	}

	public void alterar(Conta conta) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
		em.close();

	}

	public void deletar(Conta conta) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.remove(em.merge(conta));
		em.getTransaction().commit();
		em.close();

	}


}
