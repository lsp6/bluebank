package Model;

import java.math.BigDecimal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy="conta")
	private Cliente cliente;
	
	private int numeroAgencia;
	@Column(unique= true)
	private int numeroConta;
	
	private BigDecimal saldo;

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public int getNumeroAgencia() {
		return numeroAgencia;
	}



	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}



	public int getNumeroConta() {
		return numeroConta;
	}



	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}



	public BigDecimal getSaldo() {
		return saldo;
	}



	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}



	@Override
	public boolean equals(Object obj){
		if(obj.getClass() == Conta.class){
			
			if(((Conta)obj).getId() == this.getId()){
				return true;
			}
			
		}
		
		return false;
		
	}
	
	
}
