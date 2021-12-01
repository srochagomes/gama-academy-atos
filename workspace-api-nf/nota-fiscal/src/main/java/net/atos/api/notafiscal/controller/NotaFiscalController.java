package net.atos.api.notafiscal.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.service.BuscaNotaFiscalService;
import net.atos.api.notafiscal.service.CancelaNotaFiscalVendaService;
import net.atos.api.notafiscal.service.CriaNotaFiscal;

@RestController
@RequestMapping("/v1/notas-fiscais")
public class NotaFiscalController {
	

	private List<CriaNotaFiscal> criacaoNotaFiscalStrategies;
	
	private BuscaNotaFiscalService buscaNotaFiscalService;
	
	private CancelaNotaFiscalVendaService cancelaNotaFiscalVendaService;
	
	public NotaFiscalController(List<CriaNotaFiscal> strategies,
			BuscaNotaFiscalService buscaNotaFiscalService, 
			CancelaNotaFiscalVendaService cancelaService) {
		super();
		this.buscaNotaFiscalService = buscaNotaFiscalService;
		this.criacaoNotaFiscalStrategies = strategies;
		this.cancelaNotaFiscalVendaService = cancelaService;
	}




	@PostMapping(produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	public ResponseEntity<NotaFiscalVO> criaNotaFiscal(@Valid @RequestBody NotaFiscalVO notaFiscal){	
		
		
		CriaNotaFiscal criaNotaFiscal = criacaoNotaFiscalStrategies
											.stream()
											.filter(item-> item.isType(notaFiscal.getOperacaoFiscal()))
											.findFirst()
											.orElseThrow(()->new BadRequestException("Operação Fiscan Não Existe."));
		
		NotaFiscalVO notaFiscalCreated = criaNotaFiscal.persistir(notaFiscal);
		
		URI uri = MvcUriComponentsBuilder.fromController(getClass())
				.path("/{id}")
				.buildAndExpand(notaFiscalCreated.getId())
				.toUri();

		return ResponseEntity.created(uri).body(notaFiscalCreated);
	}
	
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<NotaFiscalVO> getNotaFiscalPorId(@PathVariable("id") Long id){
		
		NotaFiscalVO notaFiscalEncontrada = buscaNotaFiscalService.porId(id);			
		
		return ResponseEntity.ok(notaFiscalEncontrada);
	}
	
	
	@PatchMapping(value = "/{id}/cancelamento", produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<Long> cancelarNotaFiscalPorId(@PathVariable("id") Long id){
		
		this.cancelaNotaFiscalVendaService.cancelar(id);	
		
		return ResponseEntity.ok(id);
		
	}
	
	@GetMapping(value = "/documentos/{documento}", produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<List<NotaFiscalVO>> getNotaFiscaisPorDocumentos(@PathVariable("documento") String documento){
		
		List<NotaFiscalVO> notasFiscaisEncontradas = this.buscaNotaFiscalService.porDocumento(documento);	
		
		return ResponseEntity.ok(notasFiscaisEncontradas);
		
	}
	
	@GetMapping(value = "/emissao-periodos/{dataInicio}/{dataFim}", produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<List<NotaFiscalVO>> getNotaFiscaisPorPeriodo(
			@PathVariable("dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy")LocalDate dataInicio,
			@PathVariable("dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFim){
		
		List<NotaFiscalVO> notasFiscaisEncontradas = this.buscaNotaFiscalService.porPeriodoDataEmissao(dataInicio, dataFim);	
		
		return ResponseEntity.ok(notasFiscaisEncontradas);
		
	}
	
	
	

}
