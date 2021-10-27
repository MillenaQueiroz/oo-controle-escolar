package br.com.joao.banco.config.validacao;

import java.util.Date;

public class Exceptions {
	
	private Date data;
	private String mensagemErro;
	
	
	public Exceptions(Date data, String mensagemErro) {
		this.data = data;
		this.mensagemErro = mensagemErro;
	}


	public Exceptions() {
		
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getMensagemErro() {
		return mensagemErro;
	}


	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	
	
	

	
}
