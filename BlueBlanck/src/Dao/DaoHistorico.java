package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import Model.Historico;

@Repository("daoHistorico")
public class DaoHistorico {

	public void criar(Historico historico) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.persist(historico);
		em.getTransaction().commit();
		em.close();

	}

	public List<Historico> ler(String jpql, List <String> parameterName, List parameter) {
		
		EntityManager em = new JPAUtil().getEm();
		
		Query query = em.createQuery(jpql, Historico.class);
		
		if(parameter != null){
			for(int i = 0; i<parameterName.size(); i++){
				query.setParameter(parameterName.get(i), parameter.get(i));
			}
			
		}
		List<Historico> historico = query.getResultList();
		em.close();
		return historico;		
	}

	public void alterar(Historico historico) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.merge(historico);
		em.getTransaction().commit();
		em.close();

	}

	public void deletar(Historico historico) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.remove(historico);
		em.getTransaction().commit();
		em.close();

	}
	

}
