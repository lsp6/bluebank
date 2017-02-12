package Model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Cliente remetente;

	@ManyToOne
	private Cliente destinatario;

	private transient String tipoHistorico;

	private BigDecimal valor;
	private BigDecimal saldoAnteriorRemetente;
	private BigDecimal saldoAnteriorDestinatario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Cliente getRemetente() {
		return remetente;
	}



	public void setRemetente(Cliente remetente) {
		this.remetente = remetente;
	}



	public Cliente getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}



	public String getTipoHistorico() {
		return tipoHistorico;
	}



	public void setTipoHistorico(String tipoHistorico) {
		this.tipoHistorico = tipoHistorico;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	public BigDecimal getSaldoAnteriorRemetente() {
		return saldoAnteriorRemetente;
	}



	public void setSaldoAnteriorRemetente(BigDecimal saldoAnteriorRemetente) {
		this.saldoAnteriorRemetente = saldoAnteriorRemetente;
	}



	public BigDecimal getSaldoAnteriorDestinatario() {
		return saldoAnteriorDestinatario;
	}



	public void setSaldoAnteriorDestinatario(BigDecimal saldoAnteriorDestinatario) {
		this.saldoAnteriorDestinatario = saldoAnteriorDestinatario;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == Historico.class) {

			if (((Historico) obj).getId() == this.getId()) {
				return true;
			}

		}

		return false;

	}
	
	public static void main(String [] args){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);

		System.out.println(hours+"  "+minutes);
	}

}
