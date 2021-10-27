package br.com.joao.banco.controller.dto;

import java.math.BigDecimal;



import br.com.joao.banco.modelo.Transacoes;


public class TransacoesDto {
	
	private int numero;
	private BigDecimal valor;

	
	
	public int getNumero() {
		return numero;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public TransacoesDto(int numero, BigDecimal valor) {
		this.numero = numero;
		this.valor = valor;
	}
	
	public Transacoes toModel() {
		return new Transacoes(numero, valor);
	}
	

	


	
	
	
	
}