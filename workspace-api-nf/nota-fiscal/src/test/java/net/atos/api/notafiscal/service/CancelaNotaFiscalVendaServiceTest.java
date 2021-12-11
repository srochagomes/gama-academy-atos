package net.atos.api.notafiscal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

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
import org.springframework.context.ApplicationEventPublisher;

import net.atos.api.notafiscal.repository.NotaFiscalDevolucaoRepository;
import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalDevolucaoEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CancelaNotaFiscalVendaServiceTest {


	private CancelaNotaFiscalVendaService cancelaNotaFiscalVendaService;
	private Validator validator;
	private BuscaNotaFiscalVendaService buscaNotaFiscalVendaService;
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	private NotaFiscalDevolucaoRepository notaFiscalDevolucaoRepository;
	private ApplicationEventPublisher eventPublisher;
	
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciarCadaTeste() {
		
		this.notaFiscalRepositoy = Mockito.mock(NotaFiscalVendaRepository.class);
		this.buscaNotaFiscalVendaService = Mockito.mock(BuscaNotaFiscalVendaService.class);
		this.notaFiscalDevolucaoRepository = Mockito.mock(NotaFiscalDevolucaoRepository.class);
		this.eventPublisher = Mockito.mock(ApplicationEventPublisher.class);
		
		this.cancelaNotaFiscalVendaService = 
				new CancelaNotaFiscalVendaService(this.validator,
													this.notaFiscalRepositoy,
													this.buscaNotaFiscalVendaService,
													this.notaFiscalDevolucaoRepository, 
													this.eventPublisher);	
	}

	@Test	
	@DisplayName("Testa cancelamento nota fiscal de venda não encontrada.")	
	public void test_quandoNaoEncontraNFVendaPorID_lancaExcessao(){
		
		
		when(this.buscaNotaFiscalVendaService.porId(anyLong()))
						.thenThrow(NotFoundException.class);	
		
		var assertThrows = assertThrows(NotFoundException.class,
				()-> this.cancelaNotaFiscalVendaService.cancelar(Long.valueOf(5l)));

		assertNotNull(assertThrows);
		
	}

	@Test	
	@DisplayName("Testa cancelamento em nota fiscal de venda com devolução.")	
	public void test_quandoNotaVendaTemDevolucao_lancaExcessao(){
		
		NotaFiscalVendaEntity notaFiscalVendaTreinada = new NotaFiscalVendaEntity();
		notaFiscalVendaTreinada.setId(3l);
		
		
		
		when(this.buscaNotaFiscalVendaService.porId(anyLong()))
				.thenReturn(notaFiscalVendaTreinada);	
		
		NotaFiscalDevolucaoEntity notaFiscalDevolucaoTreinada = new NotaFiscalDevolucaoEntity();
		notaFiscalDevolucaoTreinada.setId(4l);
		
		when(this.notaFiscalDevolucaoRepository.findByIdNotaFiscalVenda(anyLong()))
					.thenReturn(Optional.of(notaFiscalDevolucaoTreinada));
		
		var assertThrows = assertThrows(BadRequestException.class,
				()-> this.cancelaNotaFiscalVendaService.cancelar(Long.valueOf(4l)));

		assertNotNull(assertThrows);
		assertEquals("Nota fiscal de venda não pode ser cancelada, pois existe uma nota fiscal de devolução associada a ela.", 
				assertThrows.getMessage());
		
	}

	@Test	
	@DisplayName("Testa cancelamento em nota fiscal com data superior a 24hrs.")	
	public void test_quandoCancelarNotaVendaSuperior24hrs_lancaExcessao(){
		
		NotaFiscalVendaEntity notaFiscalVendaTreinada = new NotaFiscalVendaEntity();
		notaFiscalVendaTreinada.setId(3l);
		LocalDateTime dataLancamento = LocalDateTime.now();
		dataLancamento = dataLancamento.minusHours(24l).minusSeconds(10l);		
		notaFiscalVendaTreinada.setDataLancamento(dataLancamento);	
		
		
		when(this.buscaNotaFiscalVendaService.porId(anyLong()))
				.thenReturn(notaFiscalVendaTreinada);
		
		when(this.notaFiscalDevolucaoRepository.findByIdNotaFiscalVenda(anyLong()))
					.thenReturn(Optional.empty());
		
		var assertThrows = assertThrows(BadRequestException.class,
				()-> this.cancelaNotaFiscalVendaService.cancelar(Long.valueOf(4l)));

		assertNotNull(assertThrows);
		assertEquals("Nota fiscal de venda não pode ser cancelada, passou o período de 24hrs.", 
				assertThrows.getMessage());
		
	}
	
	@Test	
	@DisplayName("Testa cancelamento em nota fiscal com sucesso.")	
	public void test_quandoCancelarNotaVenda_comSucesso(){
		
		NotaFiscalVendaEntity notaFiscalVendaTreinada = new NotaFiscalVendaEntity();
		notaFiscalVendaTreinada.setId(3l);
		LocalDateTime dataLancamento = LocalDateTime.now();
		dataLancamento = dataLancamento.minusHours(20l);		
		notaFiscalVendaTreinada.setDataLancamento(dataLancamento);	
		
		
		when(this.buscaNotaFiscalVendaService.porId(anyLong()))
				.thenReturn(notaFiscalVendaTreinada);
		
		when(this.notaFiscalDevolucaoRepository.findByIdNotaFiscalVenda(anyLong()))
					.thenReturn(Optional.empty());
		
		this.cancelaNotaFiscalVendaService.cancelar(Long.valueOf(4l));


		then(this.buscaNotaFiscalVendaService).should(times(1)).porId(anyLong());	

		then(this.notaFiscalDevolucaoRepository).should(times(1))
										.findByIdNotaFiscalVenda(anyLong())	;	
		
		assertEquals(Boolean.TRUE, notaFiscalVendaTreinada.getCancelada());
		
	}
	
}
