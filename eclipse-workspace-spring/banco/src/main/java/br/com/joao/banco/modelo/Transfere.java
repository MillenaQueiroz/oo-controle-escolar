package br.com.joao.banco.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transfere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numContaSaida;
	private int numContaEntrada;
	private LocalDateTime date = LocalDateTime.now();
	private BigDecimal valor;
	
	
	public Transfere(int numContaSaida, int numContaEntrada, BigDecimal valor) {
		this.numContaSaida = numContaSaida;
		this.numContaEntrada = numContaEntrada;
		this.valor = valor;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getNumContaSaida() {
		return numContaSaida;
	}


	public void setNumContaSaida(int numContaSaida) {
		this.numContaSaida = numContaSaida;
	}


	public int getNumContaEntrada() {
		return numContaEntrada;
	}


	public void setNumContaEntrada(int numContaEntrada) {
		this.numContaEntrada = numContaEntrada;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


