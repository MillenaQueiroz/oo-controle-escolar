package br.com.joao.banco.modelo;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import com.sun.istack.NotNull;

@Entity

public class Conta {
	
	@NotNull
	protected BigDecimal saldo = new BigDecimal(0);
	private int agencia;
	@NotNull @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull 
	private int numero;
	@OneToOne @NotNull
	private Cliente titular;
	

	
	
	public Conta() {
		
		
	}
	

	public Conta(int agencia, int numero, Cliente titular) {
		this.agencia = agencia;
		this.titular = titular;
		this.numero = numero;
	}

	public void saca(BigDecimal valor) {
		saldo = saldo.subtract(valor);
	}
	
	public boolean testaTransferencia(Conta conta, BigDecimal valor) {
		if (saldo.compareTo(valor) >= 0) {
			
			return true;

		} else {
			return false;
		}
	}
		
	public void deposita(BigDecimal valor) {
		saldo = saldo.add(valor);	
	}
		
		

	public int getNumero() {
		return numero;
	}






	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}



	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}

	

	


	
	
}
