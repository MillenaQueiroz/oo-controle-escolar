package br.com.joao.banco.config.validacao;

public class ErroDeFormularioDto {
	
	private String campo;
	private String mensagemErro;
	
	public ErroDeFormularioDto(String campo, String mensagemErro) {
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}
	public String getCampo() {
		return campo;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	
	
	
}
