package br.com.joao.banco.controller.form;

import com.sun.istack.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import br.com.joao.banco.modelo.Cliente;
import br.com.joao.banco.modelo.Conta;
import br.com.joao.banco.repository.ClienteRepository;


public class ContaForm {
	
	@NotNull @Min(100) @Max(999)
	private int agencia;
	
	
	@NotNull 
	private int numero;
	


	@NotNull @NotEmpty
	private String cpfCliente;
	
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	
	
	
	public Conta converter(ClienteRepository clienteRepository) {
		
		
		Cliente cliente = clienteRepository.findByCpf(cpfCliente);
		return new Conta(agencia, numero, cliente);
	}
	
	
}
