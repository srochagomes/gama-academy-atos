package net.atos.api.logistica.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelaOrdemQueueConfig {
	
	
	@Bean
	public Queue cancelaOrdemQueue() {
		return QueueBuilder
				.durable("cancela-ordem-servico")
				.deadLetterExchange("nota-fiscal")
				.deadLetterRoutingKey("nf.canceled.venda-dead-letter")
				.build();
		
	}
	
	@Bean 
	public Exchange cancelaOrdemExchange() {
		return ExchangeBuilder.topicExchange("nota-fiscal").durable(true).build();		
	}
	
	@Bean
	public Binding cancelaOrdemBinding() {
		return BindingBuilder
				.bind(this.cancelaOrdemQueue())
				.to(this.cancelaOrdemExchange())
				.with("nf.canceled.venda")
				.noargs();
	}
	
	
	@Bean
	public Queue cancelaOrdemQueueDeadLetter() {
		return QueueBuilder
				.durable("cancela-ordem-servico-deadletter")				
				.build();
		
	}
	
	@Bean
	public Binding cancelaOrdemDeadLetterBinding() {
		return BindingBuilder
				.bind(this.cancelaOrdemQueueDeadLetter())
				.to(this.cancelaOrdemExchange())
				.with("nf.canceled.venda-dead-letter")
				.noargs();
	}

}
