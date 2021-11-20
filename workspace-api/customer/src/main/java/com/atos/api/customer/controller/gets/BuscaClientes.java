package com.atos.api.customer.controller.gets;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.api.customer.controller.RootController;
import com.atos.api.customer.domain.CustomerVO;

@RestController
public class BuscaClientes extends RootController{
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
	, headers = "versao=1")
	public ResponseEntity<CustomerVO> buscar(){
		CustomerVO customer = new CustomerVO();
		
		customer.setCodigo(1);
		customer.setEndereco("Rua 1");
		customer.setNome("Antonio");
		
		return ResponseEntity.ok(customer);
		
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}
	, headers = "versao=2")
	public ResponseEntity<CustomerVO> buscarV2(){
		CustomerVO customer = new CustomerVO();
		
		customer.setCodigo(1);
		customer.setEndereco("Rua 1");
		customer.setNome("Antonio");
		
		return ResponseEntity.ok(customer);
		
		
	}
	

}
