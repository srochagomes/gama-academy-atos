package br.atos.acelera.pagtos;

public enum FormaPagamento {
	
	DINHEIRO("Dinheiro"),
	CHEQUE("Cheque"),
	CARTAO_CREDITO("Cartão de Credito"),
	PIX("Pix"),
	NAO_IDENTIFICADO;
	
	FormaPagamento(String desc){
		this.descricao = desc;
	}
	
	FormaPagamento(){
	}
	
	private String descricao;
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
	
}
