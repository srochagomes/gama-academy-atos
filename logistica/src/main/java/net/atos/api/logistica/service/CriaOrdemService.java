package net.atos.api.logistica.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.factory.OrdemServicoFactory;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;


@Service
public class CriaOrdemService {
	
	private Validator validator;

	private LogisticaRepository logisticaRepository; 
	

	public CriaOrdemService(Validator pValidator, 
			LogisticaRepository pLogisticaRepository) {
		this.validator = pValidator;
		this.logisticaRepository = pLogisticaRepository;
	}

	public OrdemServicoVO processar(OrdemServicoVO ordemServicoVO) {
		Set<ConstraintViolation<OrdemServicoVO>> 
			validateMessages = this.validator.validate(ordemServicoVO);
		
		if (!validateMessages.isEmpty()) {
			throw new ConstraintViolationException("Ordem de Serviço Inválido",validateMessages);
		}
		
		
		OrdemServicoEntity entity = new OrdemServicoFactory(ordemServicoVO).toEntity();

		entity.setDataEvento(LocalDateTime.now());
		entity = this.logisticaRepository.save(entity);
						
		ordemServicoVO.setId(entity.getId());
		
		return ordemServicoVO;


		
	}
	

}
