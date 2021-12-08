package net.atos.api.logistica.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.logistica.domain.CancelaOrdemServicoVO;
import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.factory.OrdemServicoFactory;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@Service
public class CancelaOrdemService {

	private Validator validator;

	private LogisticaRepository logisticaRepository; 
	

	public CancelaOrdemService(Validator pValidator, 
			LogisticaRepository pLogisticaRepository) {
		this.validator = pValidator;
		this.logisticaRepository = pLogisticaRepository;
	}

	@Transactional
	public OrdemServicoVO processar(CancelaOrdemServicoVO cancelaOrdemServicoVO) {
		Set<ConstraintViolation<CancelaOrdemServicoVO>> 
			validateMessages = this.validator.validate(cancelaOrdemServicoVO);
	
		if (!validateMessages.isEmpty()) {
			throw new ConstraintViolationException("Ordem de Serviço Inválido",validateMessages);
		}

		OrdemServicoEntity ordemEncontrada = logisticaRepository.findByIdNotaFiscal(cancelaOrdemServicoVO.getIdNotaFiscal())
				.orElseThrow(()-> new NotFoundException
						("Ordem de serviçõ nmão encontrada. idNotaFiscal="
									+cancelaOrdemServicoVO.getIdNotaFiscal()));
		
		ordemEncontrada.setCancelado(Boolean.TRUE);
		
		OrdemServicoVO vo = new OrdemServicoFactory(ordemEncontrada).toVO();
		
		return vo;
	}

}
