package com.atos.api.customer.controller.posts;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.atos.api.customer.controller.RootController;
import com.atos.api.customer.domain.CustomerVO;


@RestController
public class CriaCustomer extends RootController {
	
	@PostMapping(value="v1", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResponseEntity<CustomerVO> criar(@RequestBody CustomerVO customer){
		
		CustomerVO  customerResultado = new CustomerVO();
		customerResultado.setCodigo(customer.getCodigo());
		
		URI uri = MvcUriComponentsBuilder.fromController(getClass())
					.path("/v1/{id}")
					.buildAndExpand(customerResultado.getCodigo())
					.toUri();
		
		return ResponseEntity.created(uri).body(customerResultado);
	}

}
