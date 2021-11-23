package com.atos.api.customer.controller.gets;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.api.customer.controller.RootControllerV1;
import com.atos.api.customer.controller.RootControllerV2;
import com.atos.api.customer.domain.CustomerVO;
import com.atos.api.customer.repository.CustomerRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class BuscaClientesPorIdV2 extends RootControllerV2{
	
		
	private CustomerRepository repository;
	
	public BuscaClientesPorIdV2(CustomerRepository rep) {
		this.repository = rep;		
	}
	

	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation("Search account by id")
	public ResponseEntity<CustomerVO> buscar(
			@PathVariable("id") Integer codigo){
		
		return ResponseEntity.ok(repository.getByID(codigo));
		
		
	}
	

}
