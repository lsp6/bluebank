package Controladores;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Dao.DaoCliente;
import Dao.DaoConta;
import Model.Cliente;
import Model.Conta;

@Controller
public class GerenciaUsuario {

	private DaoCliente daoCliente = new DaoCliente();
	private DaoConta daoConta = new DaoConta();
	
	
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView autenticaUsuario(@RequestParam(value = "conta", required = false) String conta,
			@RequestParam(value = "agencia", required = false) String agencia,
			@RequestParam(value = "senha", required = false) String senha, HttpSession session) {
		
		ModelAndView resultado = new ModelAndView("login");
		List<Conta> contas = this.consultaConta();
		resultado.addObject("contas", contas);
		resultado.addObject("msg","");
		int a = 0;
		try {
			a = Integer.parseInt(agencia);
		} catch (NumberFormatException e) {

		}
		int b = 0;
		try {
			b = Integer.parseInt(conta);
		} catch (NumberFormatException e) {

		}

		if (a != 0 && b != 0 && senha != null && !senha.equals("")) {
			Cliente cliente = daoCliente.efetuarLogin(a, b, senha);
			if (cliente != null) {
				session.setAttribute("Usuario", cliente);
				return resultado = new ModelAndView("redirect:conta");
			}else{
				resultado.addObject("msg", "Informações de usuário incorretas");

			}

		}

		return resultado;
	}

	@RequestMapping(value = "/operacoes")
	public String operacoes() {

		return "operacoes";
	}
	
	public List<Conta> consultaConta(){
		
		String jpql = "select c from Conta c";
		
		return daoConta.ler(jpql, null, null);
	}
	
	@RequestMapping("/sair")
	public ModelAndView sair(HttpSession session){
		ModelAndView resultado = new ModelAndView("redirect:login");
		session.invalidate();;
		
		return resultado;
	}
	

	
	
}
