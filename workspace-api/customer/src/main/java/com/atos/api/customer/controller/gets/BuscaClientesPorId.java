package com.atos.api.customer.controller.gets;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atos.api.customer.controller.RootController;
import com.atos.api.customer.domain.CustomerVO;

@RestController
public class BuscaClientesPorId extends RootController{
	
	
	@GetMapping(value="v1/{id}",produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResponseEntity<CustomerVO> bustar(@PathVariable("id") Integer codigo){
		CustomerVO customer = new CustomerVO();
		
		customer.setCodigo(codigo);
		customer.setEndereco("Rua "+codigo);
		customer.setNome(""+codigo);
		
		return ResponseEntity.ok(customer);
		
		
	}

}
