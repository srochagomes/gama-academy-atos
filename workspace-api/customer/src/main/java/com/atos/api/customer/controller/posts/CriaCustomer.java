package com.atos.api.customer.controller.posts;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.atos.api.customer.controller.RootControllerV1;
import com.atos.api.customer.domain.CustomerVO;
import com.atos.api.customer.repository.CustomerRepository;


@RestController
public class CriaCustomer extends RootControllerV1 {

	@Autowired
	private CustomerRepository repository ;
	
	@PostMapping( consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResponseEntity<CustomerVO> criar(@RequestBody CustomerVO customer){
		
		CustomerVO customerSaved = repository.save(customer);
		
		URI uri = MvcUriComponentsBuilder.fromController(getClass())
					.path("/v1/{id}")
					.buildAndExpand(customerSaved.getCodigo())
					.toUri();
		
		return ResponseEntity.created(uri).body(customerSaved);
	}

}
