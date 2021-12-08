package net.atos.api.logistica.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.then;

import net.atos.api.logistica.domain.CancelaOrdemServicoVO;
import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CancelaOrdemServiceTest {

	private CancelaOrdemService cancelaOrdemService;
	
	private Validator validator;
	
	private LogisticaRepository logisticaRepository;
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciaCadaTeste() {

		this.logisticaRepository = Mockito.mock(LogisticaRepository.class);
		
		
		
		cancelaOrdemService = new CancelaOrdemService(this.validator, 
				this.logisticaRepository); 
	}
	
	@Test
	public void test_quandoNaoPassarObjetoOrdemServico_lancarExcecao() {
		assertNotNull(cancelaOrdemService);
		
		
		CancelaOrdemServicoVO cancelaOrdemServicoVO = null;
		
		var exception = assertThrows(IllegalArgumentException.class,()-> 
			cancelaOrdemService.processar(cancelaOrdemServicoVO));
		
		assertNotNull(exception);
	}

	@Test
	public void test_quandoNaoPassarAtributosObrigatorios_lancarExcecao() {
		assertNotNull(cancelaOrdemService);
		
		
		CancelaOrdemServicoVO cancelaOrdemServicoVO = new CancelaOrdemServicoVO();
		
		var exception = assertThrows(ConstraintViolationException.class,()-> 
				cancelaOrdemService.processar(cancelaOrdemServicoVO));
		
		assertNotNull(exception);
		assertEquals(1, exception.getConstraintViolations().size());
		
		List<String> mensagens = exception.getConstraintViolations()
			     .stream()
			     .map(ConstraintViolation::getMessage)
			     .collect(Collectors.toList());
	
		assertThat(mensagens, hasItems("Campo id nota fiscal não pode ser null"
				));
		
	}


	@Test
	public void test_quandoNaoEncontraOrdemServico_lancarExcecao() {
		assertNotNull(cancelaOrdemService);
		
		CancelaOrdemServicoVO cancelaOrdemServicoVO = new CancelaOrdemServicoVO();
		cancelaOrdemServicoVO.setIdNotaFiscal(1l);
		

		when(this.logisticaRepository.findByIdNotaFiscal(anyLong())).thenReturn(Optional.empty());
		
		var exception = 
				assertThrows(NotFoundException.class, ()-> cancelaOrdemService.processar(cancelaOrdemServicoVO));
		
		
		assertNotNull(exception);
		
		then(this.logisticaRepository).should(times(1)).findByIdNotaFiscal(anyLong());
		
		
		
	}


	@Test
	public void test_quandoOrdemServicoCancelada_alteraEstado() {
		assertNotNull(cancelaOrdemService);
		
		CancelaOrdemServicoVO cancelaOrdemServicoVO = new CancelaOrdemServicoVO();
		cancelaOrdemServicoVO.setIdNotaFiscal(1l);

		OrdemServicoEntity ordemEncontrada = new OrdemServicoEntity();
		ordemEncontrada.setId(1l);

		when(this.logisticaRepository.findByIdNotaFiscal(anyLong()))
					.thenReturn(Optional.of(ordemEncontrada));
		
		OrdemServicoVO ordemCancelado = cancelaOrdemService.processar(cancelaOrdemServicoVO);
		
		then(this.logisticaRepository).should(times(1)).findByIdNotaFiscal(anyLong());
		
		assertNotNull(ordemCancelado);
		assertEquals(1l, ordemCancelado.getId());		
		
		
	}

}
