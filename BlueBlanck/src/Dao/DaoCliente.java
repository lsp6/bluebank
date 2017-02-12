package Dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import Model.Conta;
import Model.Cliente;

@Repository("daoCliente")
public class DaoCliente {

	public void criar(Cliente cliente) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();

	}

	public void criarOneToOneBi(Cliente cliente, Conta conta) {

		EntityManager em = new JPAUtil().getEm();
		em.getTransaction().begin();
		em.persist(cliente);
		em.persist(conta);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<Cliente> ler(String jpql, List<String> parameterName, List parameter) {

		EntityManager em = new JPAUtil().getEm();

		Query query = em.createQuery(jpql, Cliente.class);

		if (parameter != null) {
			for (int i = 0; i < parameterName.size(); i++) {
				query.setParameter(parameterName.get(i), parameter.get(i));
			}

		}
		List<Cliente> clientes = query.getResultList();
		em.close();
		return clientes;
	}

	public void alterar(Cliente cliente) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();

	}

	public void deletar(Cliente cliente) {

		EntityManager em = new JPAUtil().getEm();

		em.getTransaction().begin();
		em.remove(cliente);
		em.getTransaction().commit();
		em.close();

	}

	public Cliente efetuarLogin(int agencia, int conta, String senha) {

		EntityManager em = new JPAUtil().getEm();
		String jpql = "select c from Cliente c where c.conta.numeroConta = :conta and c.conta.numeroAgencia= :agencia and c.senha = :senha";

		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("conta", conta);
		query.setParameter("agencia", agencia);
		query.setParameter("senha", senha);

		try {
			Cliente cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (NoResultException e) {

		}

		return null;
	}



}
