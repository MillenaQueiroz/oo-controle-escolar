package br.com.joao.banco.controller.form;

import br.com.joao.banco.modelo.Cliente;
import br.com.joao.banco.repository.ClienteRepository;

public class ClienteForm {
	
	private String nome;
	private String cpf;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Cliente converter(ClienteRepository clienteRepository) {
		
		return new Cliente(nome, cpf);
	}
	
	
}
