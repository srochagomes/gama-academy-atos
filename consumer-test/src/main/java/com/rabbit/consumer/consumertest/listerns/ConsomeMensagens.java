package com.rabbit.consumer.consumertest.listerns;

import org.apache.commons.logging.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.rabbit.consumer.consumertest.domain.OrderMessage;

@Component
@Profile("!test")
public class ConsomeMensagens {
	
	
	@Value("${FILA_PARAMETRO}")
	private String consumidor; 
	
	@Value("${FILA_ERRO}")
	private boolean erro;
	
	@RabbitListener(queues = "${FILA_PARAMETRO}")
	public void consumirMensagem(OrderMessage mensagem) {
		
		System.out.println("FILA="+consumidor);
		System.out.println("\t\t"+mensagem);
		
		if (erro) {			
			throw new RuntimeException("");
		}
		
	}
	

}
