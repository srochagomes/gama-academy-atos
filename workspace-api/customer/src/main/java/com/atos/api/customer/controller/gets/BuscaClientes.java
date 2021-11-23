package com.atos.api.customer.controller.gets;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.api.customer.conflict.InterfaceConflito;
import com.atos.api.customer.controller.RootControllerV1;
import com.atos.api.customer.domain.CustomerVO;
import com.atos.api.customer.repository.CustomerRepository;

@RestController
public class BuscaClientes extends RootControllerV1{
	
	@Autowired
	private CustomerRepository repository ;
	
	@Autowired	
	private List<InterfaceConflito> conflito;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML})
	public ResponseEntity<List<CustomerVO>> buscar(){
		
		
		
		return ResponseEntity.ok(repository.getAll());		
		
	}	
	

}
