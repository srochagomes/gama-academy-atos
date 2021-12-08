package net.atos.api.logistica.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.factory.OrdemServicoFactory;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@Service
public class ConsultaOrdemService {

	private LogisticaRepository logisticaRepository; 
	

	public ConsultaOrdemService( 
			LogisticaRepository pLogisticaRepository) {		
		this.logisticaRepository = pLogisticaRepository;
	}


	public Page<OrdemServicoVO> porPeriodo(@NotNull LocalDate inicio, @NotNull LocalDate fim, Pageable pageable) {
		
		Page<OrdemServicoEntity> ordensEncontradas = logisticaRepository
										.findByDataEmissaoBetween(inicio, fim, pageable);
		
		if(ordensEncontradas.isEmpty()) {
			throw new NotFoundException("Nenhuma operação fiscal para o periodo informado");	
		}
		
		return new PageImpl<>(ordensEncontradas.stream()
				.map(OrdemServicoFactory::new)
				.map(OrdemServicoFactory::toVO)
				.collect(Collectors.toList()),
				ordensEncontradas.getPageable(),
				ordensEncontradas.getTotalElements());
	}


}
