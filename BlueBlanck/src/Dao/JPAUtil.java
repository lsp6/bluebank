package Dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import Model.Cliente;
import Model.Conta;

@WebServlet
public class JPAUtil implements Servlet {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banco");
	private EntityManager em;
	private String [] nomes ={"João", "Pedro", "Agusto", "Ana", "Flávio", "Lucas", "Caroline", "Antônio", "José", "Matheus"};
	private String [] sobrenomes ={"Souza", "Dumbra", "Mesquita", "Pereira", "Feitosa", "Oliveira", "Mota", "Merbold", "Santos", "Guimarães"};
	public EntityManager getEm() {

		em = emf.createEntityManager();

		return em;
	}

	private void criaDados() {
		DaoConta daoConta = new DaoConta();
		DaoCliente daoCliente = new DaoCliente();
		Random random = new Random();
		int r = 0;

		for (int i = 0; i < 10; i++) {
			Cliente cliente = new Cliente();
			Conta conta = new Conta();
			String montaCpf = null;
			String montaRg = null;
			String montaAgencia = null;
			String montaConta = null;

			for (int j = 0; j < 11; j++) {

				if (j == 0) {
					montaCpf = Integer.toString(random.nextInt(10));
				} else {
					montaCpf = montaCpf + Integer.toString(random.nextInt(10));
					if (j == 2 || j == 5) {
						montaCpf = montaCpf + ".";
					} else if (j == 8) {
						montaCpf = montaCpf + "-";
					}
				}

			}

			for (int j = 0; j < 9; j++) {
				if (j == 0) {
					montaRg = Integer.toString(random.nextInt(10));
				} else {
					montaRg = montaRg + Integer.toString(random.nextInt(10));
					if (j == 1 || j == 4) {
						montaRg = montaRg + ".";
					} else if (j == 7) {
						montaRg = montaRg + "-";
					}
				}

			}

			cliente.setCpf(montaCpf);
			cliente.setRg(montaRg);
			cliente.setNome(nomes[i]);
			cliente.setSobrenome(sobrenomes[i]);
			cliente.setSenha("1234");

			for (int j = 0; j < 4; j++) {

				if (j == 0) {

					r = random.nextInt(10);
					do {
						r = random.nextInt(10);
					} while (r == 0);
					montaAgencia = Integer.toString(r);

				} else {

					montaAgencia = montaAgencia + Integer.toString(random.nextInt(10));
				}

			}

			for (int j = 0; j < 6; j++) {

				if (j == 0) {
					r = random.nextInt(10);
					do {
						r = random.nextInt(10);
					} while (r == 0);
					montaConta = Integer.toString(r);
				} else {
					montaConta = montaConta + Integer.toString(random.nextInt(10));
				}
				

			}

			conta.setNumeroAgencia(Integer.parseInt(montaAgencia));
			conta.setNumeroConta(Integer.parseInt(montaConta));

			conta.setSaldo(new BigDecimal(random.nextDouble() * 10000).setScale(2, BigDecimal.ROUND_DOWN));
			
			cliente.setConta(conta);
			conta.setCliente(cliente);

			daoCliente.criarOneToOneBi(cliente, conta);

		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		criaDados();

	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
