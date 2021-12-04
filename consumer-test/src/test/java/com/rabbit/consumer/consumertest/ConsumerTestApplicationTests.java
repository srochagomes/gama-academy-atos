package com.rabbit.consumer.consumertest;

import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rabbit.consumer.consumertest.domain.OrderMessage;
import com.rabbit.consumer.consumertest.domain.OrderVO;

@SpringBootTest
@ActiveProfiles("test")
class ConsumerTestApplicationTests {

	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test	
	void contextLoads() {
		int numero = 0;
		
		for(int i=0;i<500;i++) {
		
			numero = (int)(new Random().nextDouble()*100)%2;
			
			if(numero==0) {
				this.enviarDelivery();
			}else {
				this.enviarPresencial();	
			}
			
		}
		
		
		
		
	}
	
	
	private void enviarDelivery() {
		OrderMessage orderMessage = new OrderMessage();
		OrderVO order = new OrderVO();
		order.setNumero(100l);
		orderMessage.setContent(order );
		orderMessage.setDestino("Delivery");
		orderMessage.setMensagem("Entregar na rua xxxx");
		
		rabbitTemplate.convertAndSend("order.publish", "order.created.delivery", orderMessage);
		
	}

	private void enviarPresencial() {
		OrderMessage orderMessage = new OrderMessage();
		OrderVO order = new OrderVO();
		order.setNumero(100l);
		orderMessage.setContent(order );
		orderMessage.setDestino("Presencial");
		orderMessage.setMensagem("Entregar na mesa ");
		
		rabbitTemplate.convertAndSend("order.publish", "order.created.presencial", orderMessage);
		
	}
	
}
