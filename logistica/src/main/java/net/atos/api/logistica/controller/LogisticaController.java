package net.atos.api.logistica.controller;

import java.time.LocalDate;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.service.ConsultaOrdemService;


@RestController
@RequestMapping("/api/ordens-servicos")
public class LogisticaController {
	
	private ConsultaOrdemService consultaOrdemService;
	
	public LogisticaController(ConsultaOrdemService pConsultaOrdemService) {
		this.consultaOrdemService = pConsultaOrdemService;		
	}
	
	
	@GetMapping("/periodo-inicial/{inicio}/periodo-final/{fim}")
	public ResponseEntity<Page<OrdemServicoVO>> buscaOrdemServicoPorPeriodo(
						@PathVariable("inicio") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicial,
						@PathVariable("fim") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFinal,
						@ParameterObject @PageableDefault(sort = {
						"dataEmissao" }, direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		
		Page<OrdemServicoVO> servicosConmsultado = consultaOrdemService.porPeriodo(dataInicial, dataFinal, pageable);
		
		return ResponseEntity.ok(servicosConmsultado);
	}

}
