package br.com.joao.banco.controller.dto;

import java.math.BigDecimal;

import br.com.joao.banco.modelo.Transfere;



public class TransfereDto {
	
	private int numContaSaida;
	private int numContaEntrada;
	private BigDecimal valor;
	
	public TransfereDto(int numContaSaida, int numContaEntrada, BigDecimal valor) {
		this.numContaSaida = numContaSaida;
		this.numContaEntrada = numContaEntrada;
		this.valor = valor;
	}

	public Transfere toModel() {
		return new Transfere(numContaSaida, numContaEntrada, valor);
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	
}
