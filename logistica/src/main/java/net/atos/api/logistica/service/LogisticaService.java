package net.atos.api.logistica.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.factory.OrdemServicoFactory;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;


@Service
public class LogisticaService {
	
	private Validator validator;
	

	public LogisticaService(Validator pValidator) {
		this.validator = pValidator;
	}

	public OrdemServicoVO criarOrdemServico(OrdemServicoVO ordemServicoVO) {
		Set<ConstraintViolation<OrdemServicoVO>> 
			validateMessages = this.validator.validate(ordemServicoVO);
		
		if (!validateMessages.isEmpty()) {
			throw new ConstraintViolationException("Ordem de Serviço Inválido",validateMessages);
		}
		
		
		OrdemServicoEntity entity = new OrdemServicoFactory(ordemServicoVO).toEntity();
		
		
		return ordemServicoVO;


		
	}

}
