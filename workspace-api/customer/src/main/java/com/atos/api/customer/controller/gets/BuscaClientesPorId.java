package com.atos.api.customer.controller.gets;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.api.customer.controller.RootController;
import com.atos.api.customer.domain.CustomerVO;

import io.swagger.annotations.ApiOperation;

@RestController
public class BuscaClientesPorId extends RootController{
	
	@GetMapping(value="/v1/{id}",produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation("Search account by id")
	public ResponseEntity<CustomerVO> buscar(
			@PathVariable("id") Integer codigo,
			@RequestParam(required = false) String nome,
			@RequestHeader Map<String, List<String>> headers){
		
		
		CustomerVO customer = new CustomerVO();
		
		customer.setCodigo(codigo);
		customer.setEndereco("Rua "+codigo);
		customer.setNome(nome);
		
		return ResponseEntity.ok(customer);
		
		
	}
	

}
