package Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@Column(length = 14)
	private String cpf;
	
	@Column(length = 12)
	private String rg;
	private String nome;
	private String sobrenome;
	
	private String senha;
	
	@OneToOne
	private Conta conta;
	
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta = conta;
	}


	@Override
	public boolean equals(Object obj){
		if(obj.getClass() == Cliente.class){
			
			if(((Cliente)obj).getCpf() == this.getCpf()){
				return true;
			}
			
		}
		
		return false;
		
	}
	
}
