package net.atos.api.logistica.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CriaOrdemServiceTest {
	
	private CriaOrdemService logisticaService;
	
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
		
		
		
		logisticaService = new CriaOrdemService(this.validator, 
				this.logisticaRepository); 
	}

	
	@Test
	public void test_quandoNaoPassarObjetoOrdemServico_lancarExcecao() {
		assertNotNull(logisticaService);
		
		
		OrdemServicoVO ordemServicoVO = null;
		
		var exception = assertThrows(IllegalArgumentException.class,()-> 
					logisticaService.processar(ordemServicoVO));
		
		assertNotNull(exception);
	}
	
	@Test
	public void test_quandoNaoPassarAtributosObrigatorios_lancarExcecao() {
		assertNotNull(logisticaService);
		
		
		OrdemServicoVO ordemServicoVO = new OrdemServicoVO();
		
		var exception = assertThrows(ConstraintViolationException.class,()-> 
					logisticaService.processar(ordemServicoVO));
		
		assertNotNull(exception);
		assertEquals(3, exception.getConstraintViolations().size());
		
		List<String> mensagens = exception.getConstraintViolations()
			     .stream()
			     .map(ConstraintViolation::getMessage)
			     .collect(Collectors.toList());
	
		assertThat(mensagens, hasItems("Campo id nota fiscal não pode ser null",
				"Campo valor não pode ser null",
				"Campo data de emissão não pode ser nula"
				));
		
	}
	
	@Test
	public void test_quandoRegistrarOrdemServicoEUniqueKeyViolation_lancarExcecao() {
		assertNotNull(logisticaService);
		
		OrdemServicoVO ordemServicoVO = new OrdemServicoVO();
		ordemServicoVO.setDataEmissao(LocalDate.now());
		ordemServicoVO.setIdNotaFiscal(1l);
		ordemServicoVO.setValor(BigDecimal.ONE);
		
		when(this.logisticaRepository.save(any()))
				.thenThrow(org.hibernate.exception.ConstraintViolationException.class);
		
		var exception = 
				assertThrows(org.hibernate.exception.ConstraintViolationException.class,
						()->logisticaService.processar(ordemServicoVO));
		
		assertNotNull(exception);
		
		
		
	}	

	@Test
	public void test_quandoRegistrarOrdemServico_criaOrdem() {
		assertNotNull(logisticaService);
		
		OrdemServicoVO ordemServicoVO = new OrdemServicoVO();
		ordemServicoVO.setDataEmissao(LocalDate.now());
		ordemServicoVO.setIdNotaFiscal(1l);
		ordemServicoVO.setValor(BigDecimal.ONE);
		
		OrdemServicoEntity entitySaved = new OrdemServicoEntity();
		entitySaved.setId(1l);
		
		when(this.logisticaRepository.save(any()))
				.thenReturn(entitySaved);
		
		OrdemServicoVO ordemServicoCriado = logisticaService.processar(ordemServicoVO);
		
		then(this.logisticaRepository).should(times(1)).save(any());
				
		assertNotNull(ordemServicoCriado);
		assertEquals(1l,ordemServicoCriado.getId());		
		
	}	
	
}
