package com.atos.api.customer.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.atos.api.customer.domain.CustomerVO;

@Component
public class CustomerRepository {
	
	private Map<Integer, CustomerVO> customers;
	
		
	public CustomerRepository(Map<Integer, CustomerVO> cust, CustomerVO customer) {
		this.customers = cust;
		
	}
	
	
	public CustomerVO save(final CustomerVO customer) {
		Integer id = Optional.ofNullable(customer.getCodigo()).orElseGet(()->customers.size()+1);
		customer.setCodigo(id);
		customers.put(id, customer);
		return customer; 
	}
	
	public CustomerVO getByID(final Integer id) {
		
		CustomerVO customerVO = customers.get(id);
		
		return Optional.ofNullable(customerVO)
				.orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
		
	}
	
	
	public List<CustomerVO> getAll() {
		return customers.values().stream().collect(Collectors.toList());
	}
	

}
