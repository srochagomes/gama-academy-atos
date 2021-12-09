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
public class CriaOrdemQueueConfig {
	
	
	@Bean
	public Queue criaOrdemQueue() {
		return QueueBuilder
				.durable("cria-ordem-servico")
				.deadLetterExchange("nota-fiscal")
				.deadLetterRoutingKey("nf.created.venda-dead-letter")
				.build();
		
	}
	
	@Bean 
	public Exchange criaOrdemExchange() {
		return ExchangeBuilder.topicExchange("nota-fiscal").durable(true).build();		
	}
	
	@Bean
	public Binding criaOrdemBinding() {
		return BindingBuilder
				.bind(this.criaOrdemQueue())
				.to(this.criaOrdemExchange())
				.with("nf.created.venda")
				.noargs();
	}
	
	
	@Bean
	public Queue criaOrdemQueueDeadLetter() {
		return QueueBuilder
				.durable("cria-ordem-servico-deadletter")				
				.build();
		
	}
	
	@Bean
	public Binding criaOrdemDeadLetterBinding() {
		return BindingBuilder
				.bind(this.criaOrdemQueueDeadLetter())
				.to(this.criaOrdemExchange())
				.with("nf.created.venda-dead-letter")
				.noargs();
	}

}
