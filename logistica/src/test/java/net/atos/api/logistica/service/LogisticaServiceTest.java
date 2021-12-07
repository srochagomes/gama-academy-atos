package net.atos.api.logistica.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
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
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.logistica.domain.OrdemServicoVO;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LogisticaServiceTest {
	
	private LogisticaService logisticaService;
	
	private Validator validator;	
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciaCadaTeste() {
	
		logisticaService = new LogisticaService(this.validator); 
	}

	
	@Test
	public void test_quandoNaoPassarObjetoOrdemServico_lancarExcecao() {
		assertNotNull(logisticaService);
		
		
		OrdemServicoVO ordemServicoVO = null;
		
		var exception = assertThrows(IllegalArgumentException.class,()-> 
					logisticaService.criarOrdemServico(ordemServicoVO));
		
		assertNotNull(exception);
	}
	
	@Test
	public void test_quandoNaoPassarAtributosObrigatorios_lancarExcecao() {
		assertNotNull(logisticaService);
		
		
		OrdemServicoVO ordemServicoVO = new OrdemServicoVO();
		
		var exception = assertThrows(ConstraintViolationException.class,()-> 
					logisticaService.criarOrdemServico(ordemServicoVO));
		
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
	public void test_quandoRegistrarOrdemServicoEUniqueKeyViolar_lancarExcecao() {
		assertNotNull(logisticaService);
		
		OrdemServicoVO ordemServicoVO = new OrdemServicoVO();
		ordemServicoVO.setDataEmissao(LocalDate.now());
		ordemServicoVO.setIdNotaFiscal(1l);
		ordemServicoVO.setValor(BigDecimal.ONE);
		
		var exception = 
				assertThrows(SQLIntegrityConstraintViolationException.class,
						()->logisticaService.criarOrdemServico(ordemServicoVO));
		
		assertNotNull(exception);
		
		
		
	}	

}
