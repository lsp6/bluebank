package Controladores;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

//hbase
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Dao.DaoCliente;
import Dao.DaoConta;
import Dao.DaoHistorico;
import Model.Cliente;
import Model.Conta;
import Model.Historico;

@Controller
public class GerenciaConta {

	private DaoHistorico daoHistorico = new DaoHistorico();
	private DaoConta daoConta = new DaoConta();
	private boolean verificaFormularioEntrada = false;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar cal;
	
	@RequestMapping(value = "/conta")
	public ModelAndView conta(HttpSession session) {
		ModelAndView model = new ModelAndView("conta");
		Cliente usuario = (Cliente) session.getAttribute("Usuario");
		verificaFormularioEntrada = false;
		model.addObject("usuario", usuario.getNome());
		model.addObject("cpf", usuario.getCpf());
		model.addObject("rg", usuario.getRg());
		model.addObject("saldo", usuario.getConta().getSaldo().doubleValue());

		String jpql = "select h from Historico h where h.remetente = :usuario or h.destinatario = :usuario order by h.data asc";
		List<String> parameterName = new ArrayList<String>();
		List<Object> parameter = new ArrayList<Object>();
		parameterName.add("usuario");
		parameter.add(usuario);
		List<Historico> historico = daoHistorico.ler(jpql, parameterName, parameter);
		
		int size = historico.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				
				
				
				
				if (historico.get(i).getRemetente().getCpf().equals(usuario.getCpf())) {

					historico.get(i).setTipoHistorico("Transferência");
					historico.get(i).setValor(new BigDecimal(-historico.get(i).getValor().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
					
					
				} else {

					historico.get(i).setTipoHistorico("Depósito");
					historico.get(i).setValor(new BigDecimal(+historico.get(i).getValor().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
				}

			}
		} else {
			model.addObject("movimentacao", "Não foi feita nenhuma movimentação");
		}

		model.addObject("historico", historico);

		return model;
	}

	@RequestMapping(value = "/transferencias", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView transacoes(@RequestParam(value = "agencia", required = false) String agencia,
			@RequestParam(value = "conta", required = false) String conta,
			@RequestParam(value = "data", required = false) String data,
			@RequestParam(value = "valor", required = false) String valor, HttpSession session) {

		Cliente usuario = (Cliente) session.getAttribute("Usuario");
		ModelAndView resultado = new ModelAndView("transferencias");
		List<Conta> contas = this.consultaConta();
		contas.remove(usuario.getConta());
		resultado.addObject("contas", contas);
		
		
		
		if (agencia != null && !agencia.equals("") && conta != null && !conta.equals("") && data != null
				&& !data.equals("") && valor != null && !valor.equals("")) {

			DaoConta daoConta = new DaoConta();
			DaoHistorico daoHistorico = new DaoHistorico();
			Conta contaCliente;
			Historico historico;
			double valorTransferir = 0;
			cal = Calendar.getInstance();
			
			Date dataHistorico;
			resultado.addObject("efetuado", false);
			BigDecimal saldoAnteriorRemetente;
			BigDecimal saldoAnteriorDestinatario;
			try {
				List<String> parameterName = new ArrayList<String>();
				List<Object> parameter = new ArrayList<Object>();
				valor = valor.replaceAll("\\.", "");
				valor = valor.replaceAll("\\,", ".");
				
				valorTransferir = Double.parseDouble(valor);

				parameterName.add("numeroAgencia");
				parameterName.add("numeroConta");
				int numeroAgencia = Integer.parseInt(agencia);
				int numeroConta = Integer.parseInt(conta);
				parameter.add(numeroAgencia);
				parameter.add(numeroConta);

				contaCliente = daoConta
						.ler("select c from Conta c where c.numeroAgencia = :numeroAgencia and c.numeroConta = :numeroConta",
								parameterName, parameter)
						.get(0);
				
				if (valorTransferir > usuario.getConta().getSaldo().doubleValue()) {
				
					resultado.addObject("msg",
							"Operação não pôde ser realizada. Saldo inferior ao valor digitado!");

					return resultado;
				}
				saldoAnteriorRemetente = usuario.getConta().getSaldo();
				saldoAnteriorDestinatario = contaCliente.getSaldo();
				valorTransferir = valorTransferir + contaCliente.getSaldo().doubleValue();
				
				contaCliente.setSaldo(new BigDecimal(valorTransferir).setScale(2,BigDecimal.ROUND_HALF_DOWN));
				
				daoConta.alterar(contaCliente);
				
				valorTransferir = usuario.getConta().getSaldo().doubleValue() - Double.parseDouble(valor);
				usuario.getConta().setSaldo(new BigDecimal(valorTransferir).setScale(2,BigDecimal.ROUND_HALF_DOWN));
				
				daoConta.alterar(usuario.getConta());

				historico = new Historico();
				historico.setDestinatario(contaCliente.getCliente());
				historico.setRemetente(usuario);
				valorTransferir = Double.parseDouble(valor);
				historico.setValor(new BigDecimal(valorTransferir).setScale(2,BigDecimal.ROUND_HALF_DOWN));
				historico.setSaldoAnteriorDestinatario(saldoAnteriorDestinatario);
				historico.setSaldoAnteriorRemetente(saldoAnteriorRemetente);
				
				dataHistorico = sdf.parse(data);
				
				cal.setTime(new Date());
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int minutes = cal.get(Calendar.MINUTE);
				int seconds = cal.get(Calendar.SECOND);
				
				cal.setTime(dataHistorico);
				cal.set(Calendar.HOUR_OF_DAY, hours);
				cal.set(Calendar.MINUTE, minutes);
				cal.set(Calendar.SECOND, seconds);
				
				historico.setData(cal.getTime());

				daoHistorico.criar(historico);
				resultado.addObject("efetuado", true);
				verificaFormularioEntrada = false;
				return resultado = new ModelAndView("redirect:conta");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resultado.addObject("efetuado", false);
			} catch (ParseException e) {
				resultado.addObject("efetuado", false);
			} catch (NullPointerException e) {
				resultado.addObject("efetuado", false);
			} catch (IndexOutOfBoundsException e) {
				resultado.addObject("msg", "Não foi possível efetuar transferência! Agência ou conta Inválidos!");
				return resultado;
			}

		}else if(verificaFormularioEntrada){
			resultado.addObject("msg",
					"Todos os campos devem ser preenchidos");
		}
		verificaFormularioEntrada = true;
		
		return resultado;
	}

	
	
	
	
	public List<Conta> consultaConta() {

		String jpql = "select c from Conta c";

		return daoConta.ler(jpql, null, null);
	}

}
