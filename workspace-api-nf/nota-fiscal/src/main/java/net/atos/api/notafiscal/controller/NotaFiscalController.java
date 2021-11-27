package net.atos.api.notafiscal.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.service.CriaNotaFiscalVendaService;

@RestController
@RequestMapping("/v1/notas-fiscais")
public class NotaFiscalController {
	

	private CriaNotaFiscalVendaService criaNotaFiscalVendaService;
	
	public NotaFiscalController(CriaNotaFiscalVendaService pCriaNotaFiscalVendaService) {
		super();
		this.criaNotaFiscalVendaService = pCriaNotaFiscalVendaService;
	}




	@PostMapping(produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	public ResponseEntity<NotaFiscalVO> criaNotaFiscal(@Valid @RequestBody NotaFiscalVO notaFiscal){
		
		NotaFiscalVO notaFiscalVO = criaNotaFiscalVendaService.persistir(notaFiscal);
		
		return ResponseEntity.ok().body(notaFiscalVO);
	}

}
