package com.rabbit.consumer.consumertest.domain;

public class OrderMessage {
	
	private OrderVO content;
	
	private String mensagem;
	
	private String destino;

	public OrderVO getContent() {
		return content;
	}

	public void setContent(OrderVO content) {
		this.content = content;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "OrderMessage [content=" + content + ", mensagem=" + mensagem + ", destino=" + destino + "]";
	}

}
