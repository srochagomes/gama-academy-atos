package net.atos.api.notafiscal.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;
import net.atos.api.notafiscal.repository.NotaFiscalRepository;
import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CriaNotaFiscalVendaServiceTest {
	
	
	private CriaNotaFiscalVendaService criaNotaFiscal;
	
	private Validator validator;
	
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	
	private RabbitTemplate rabbitTemplate;
	
	
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciarCadaTeste() {
		
		this.notaFiscalRepositoy = Mockito.mock(NotaFiscalVendaRepository.class);
		this.rabbitTemplate = Mockito.mock(RabbitTemplate.class);
		
		criaNotaFiscal = new CriaNotaFiscalVendaService(validator, notaFiscalRepositoy, this.rabbitTemplate);	
	}

	@Test
	@DisplayName("Testa quando a nota fiscal é nula.")
	void test_quando_notaFiscal_Eh_Null_LancarExcecao() {

		assertNotNull(criaNotaFiscal);

		NotaFiscalVO notaFiscal =  null;

		var assertThrows = assertThrows(IllegalArgumentException.class, ()->
							criaNotaFiscal.persistir(notaFiscal));
		
		assertNotNull(assertThrows);
		
	}

	
	
	@Test
	@DisplayName("Testa os campos da nota fiscal com obrigatoriedade.")
	void test_quando_TodosCamposObrigatorios_Eh_Null_LancarExcecao() {

		assertNotNull(criaNotaFiscal);

		NotaFiscalVO notaFiscal =  new NotaFiscalVO();

		var assertThrows = assertThrows(ConstraintViolationException.class, ()->
							criaNotaFiscal.persistir(notaFiscal));
		
		assertEquals(6, assertThrows.getConstraintViolations().size());
		List<String> mensagens = assertThrows.getConstraintViolations()
		     .stream()
		     .map(ConstraintViolation::getMessage)
		     .collect(Collectors.toList());


		assertThat(mensagens, hasItems("Campo data de emissão não pode ser nula",
				"Campo data de Lançamento não pode ser nula",
				"Campo Operacao Fiscal não pode ser nulo",
				"Campo Valor não pode ser nulo",
				"Campo Documento não pode ser nulo",
				"Campo Itens deve ter pelo menos um item"
				));
		
	}


	@Test	
	@DisplayName("Testa obrigatoriedade do campo codigo produto quando tem itens.")
	public void test_quando_temUmItemEcodigoProdutoNulo_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.VENDA);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("1-91");
		
		ItemVO item = new ItemVO();
		notaFiscal.add(item);
		
		var assertThrows = assertThrows(ConstraintViolationException.class, ()->
			criaNotaFiscal.persistir(notaFiscal));

		assertEquals(3, assertThrows.getConstraintViolations().size());
		List<String> mensagens = assertThrows.getConstraintViolations()
		     .stream()
		     .map(ConstraintViolation::getMessage)
		     .collect(Collectors.toList());
		
		assertThat(mensagens, hasItems("Campo Codigo produto não pode ser nulo",
				"Campo NCM não pode ser nulo",
				"Campo Valor do Item não pode ser nulo"
				));
		
	}


	@Test	
	@DisplayName("Testa data de emissão diferente do dia atual.")
	public void test_quando_dataDiferenteDataAtual_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now().minusDays(1l));		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.VENDA);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("1-91");
		
		ItemVO item = new ItemVO();
		item.setCodigoProduto(123);
		item.setNcm("AB-092892");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
			criaNotaFiscal.persistir(notaFiscal));

		
		assertEquals(assertThrows.getMessage(),"A data de emissão deve ser atual.");
		
		
		
	}
	
	
	@Test	
	@DisplayName("Testa a persistencia da nota fiscal.")
	public void test_quando_dadosPreenchidos_notaFiscalCriada() {
		assertNotNull(criaNotaFiscal);	
		
		NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.VENDA);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("1-91");
		
		ItemVO item = new ItemVO();
		item.setCodigoProduto(123);
		item.setNcm("AB-092892");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
		
		criaNotaFiscal.persistir(notaFiscal);
		
		then(notaFiscalRepositoy).should(times(1)).save(any());
		
	}

}
