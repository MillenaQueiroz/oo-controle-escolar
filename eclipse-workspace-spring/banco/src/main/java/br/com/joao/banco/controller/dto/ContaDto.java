package br.com.joao.banco.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import br.com.joao.banco.modelo.Conta;

public class ContaDto {
	
	private int agencia;
	private int numero;
	private BigDecimal saldo;
	private Long id;

	
	public Long getId() {
		return id;
	}








	public ContaDto(Conta conta) {
		this.agencia = conta.getAgencia();
		this.numero = conta.getNumero();
		this.saldo = conta.getSaldo();
		this.id = conta.getId();
	
	}
	
	
	




	public BigDecimal getSaldo() {
		return saldo;
	}




	public int getAgencia() {
		return agencia;
	}


	public int getNumero() {
		return numero;
	}





	public static List<ContaDto> converter(List<Conta> contas) {
		
		return contas.stream().map(ContaDto::new).collect(Collectors.toList());
	}
	
	
	
}
