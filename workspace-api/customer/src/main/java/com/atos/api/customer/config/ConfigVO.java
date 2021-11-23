package com.atos.api.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atos.api.customer.domain.CustomerVO;

@Configuration
public class ConfigVO {
	
	@Bean 
	public CustomerVO criaCustomerVO() {
		return new CustomerVO();
		
	}

}
