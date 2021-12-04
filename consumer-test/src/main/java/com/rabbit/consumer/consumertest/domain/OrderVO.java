package com.rabbit.consumer.consumertest.domain;

public class OrderVO {
	
	private Long numero;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "OrderVO [numero=" + numero + "]";
	}	
	
	

}
