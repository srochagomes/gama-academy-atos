package com.rabbit.consumer.consumertest.listerns;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsomeMensagens {
	
	
	@Value("${FILA_PARAMETRO}")
	private String consumidor; 
	
	@RabbitListener(queues = "${FILA_PARAMETRO}")
	public void consumirMensagem(String mensagem) {
		
		System.out.println("Consumidor ="+consumidor + " mensagem ="+mensagem);
		
	}
	

}
