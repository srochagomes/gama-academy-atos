package net.atos.api.logistica.conf;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitConfig {
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter(this.objectMapper());
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(this.jsonMessageConverter());
		return rabbitTemplate;
		
	}
	
	@Bean 
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
				.addModule(new JavaTimeModule())		        
		        .build();
	}


}
