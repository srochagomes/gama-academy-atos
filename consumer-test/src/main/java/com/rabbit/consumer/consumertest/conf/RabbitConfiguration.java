package com.rabbit.consumer.consumertest.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class RabbitConfiguration {

	@Bean
	public Queue queueCozinha() {
		return QueueBuilder				
				.durable("cozinha-spring")				
				.deadLetterExchange("cozinha.deadletter")
				.deadLetterRoutingKey("cozinha.deadletter")				
				.deliveryLimit(5)				
				.build();	
	}
	
	@Bean
	public Exchange exchangeCozinha() {
			return ExchangeBuilder
					.topicExchange("order.publish")										
					.build();					
	}
	
	
	@Bean
	public Binding bindingCozinha() {
		return BindingBuilder
				.bind(this.queueCozinha())
				.to(this.exchangeCozinha())
				.with("order.created.*")
				.noargs();
					
		
	}
	
	@Bean
	public Queue queueCozinhaDeadLetter() {
		return QueueBuilder				
				.durable("cozinha-deadletter-spring")
				.autoDelete()	
				.build();	
	}
	
	@Bean
	public Exchange exchangeCozinhaDeadLetter() {
			return ExchangeBuilder
					.topicExchange("cozinha.deadletter")										
					.build();
	}

	@Bean
	public Binding bindingCozinhaDeadLetter() {
		return BindingBuilder
				.bind(this.queueCozinhaDeadLetter())
				.to(this.exchangeCozinhaDeadLetter())
				.with("cozinha.deadletter")
				.noargs();
					
		
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(this.jsonMessageConverter());
		return rabbitTemplate;
		
	}
	
		
	

}
