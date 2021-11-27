package net.atos.api.notafiscal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.repository.NotaFiscalRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BuscaNotaFiscalServiceTest {

	private BuscaNotaFiscalService buscaNotaFiscalService;
	
	private Validator validator;
	
	private NotaFiscalRepository notaFiscalRepositoy;
	
	
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciarCadaTeste() {
		
		this.notaFiscalRepositoy = Mockito.mock(NotaFiscalRepository.class);
		
		buscaNotaFiscalService = new BuscaNotaFiscalService(validator, notaFiscalRepositoy);	
	}
	
	
	
	@Test	
	@DisplayName("Testa consultas vazias de notas fiscal por documento.")	
	public void test_quandoNaoEncontraNFPorDocumento_lancaExcessao(){
		
		assertNotNull(this.buscaNotaFiscalService);
		
		var assertThrows = 
				assertThrows(NotFoundException.class, 
						()-> this.buscaNotaFiscalService.porDocumento("0000"));
		
		assertNotNull(assertThrows);
		assertEquals("Nenhuma nota fiscal para o documento informado", assertThrows.getMessage());
	}
	
	
	@Test	
	@DisplayName("Testa consultas de notas fiscal por documento.")	
	public void test_quandoEncontraNFPorDocumento_retornaLista(){
		
		assertNotNull(this.buscaNotaFiscalService);
		
		List<NotaFiscalEntity> notasTreinadas = new ArrayList<>();
		notasTreinadas.add(new NotaFiscalEntity());
		notasTreinadas.add(new NotaFiscalEntity());
		notasTreinadas.add(new NotaFiscalEntity());
		
		
		when(this.notaFiscalRepositoy.findByDocumento("4"))
					.thenReturn(Optional.of(notasTreinadas));
		
		List<NotaFiscalVO> notasEncontradas = this.buscaNotaFiscalService.porDocumento("4");
		
		then(this.notaFiscalRepositoy).should(times(1)).findByDocumento("4");
		assertNotNull(notasEncontradas);
		assertEquals(3, notasEncontradas.size());
		
		
	}
	
	
	@Test	
	@DisplayName("Testa consultas vazias de notas fiscal por periodo data emissao.")	
	public void test_quandoNaoEncontraNFPorPeriodoDTEmissao_lancaExcessao(){
		
		assertNotNull(this.buscaNotaFiscalService);
		LocalDate dataInicio = LocalDate.now().minusDays(10l);
		LocalDate dataFim = LocalDate.now();
		
		var assertThrows = 
				assertThrows(NotFoundException.class, 
						()-> this.buscaNotaFiscalService.porPeriodoDataEmissao(dataInicio, dataFim));
		
		assertNotNull(assertThrows);
		assertEquals("Nenhuma nota fiscal para o periodo informado", assertThrows.getMessage());
	}

	@Test	
	@DisplayName("Testa consultas notas fiscal por periodo data emissao.")	
	public void test_quandoEncontraNFPorPeriodoDTEmissao_retornaLista(){
		
		assertNotNull(this.buscaNotaFiscalService);
		LocalDate dataInicio = LocalDate.now().minusDays(10l);
		LocalDate dataFim = LocalDate.now();
		
		List<NotaFiscalEntity> notasTreinadas = new ArrayList<>();
		notasTreinadas.add(new NotaFiscalEntity());
		notasTreinadas.add(new NotaFiscalEntity());
		notasTreinadas.add(new NotaFiscalEntity());
		
		
		when(this.notaFiscalRepositoy.findByDataEmissaoBetween(any(),any()))
					.thenReturn(Optional.of(notasTreinadas));

		
		List<NotaFiscalVO> notasEncontradas = this.buscaNotaFiscalService.porPeriodoDataEmissao(dataInicio, dataFim);
		
		then(this.notaFiscalRepositoy).should(times(1)).findByDataEmissaoBetween(any(), any());
		
		assertNotNull(notasEncontradas);
		assertEquals(3, notasEncontradas.size());
		
	}
	
	
	@Test	
	@DisplayName("Testa Quando não encontra uma nota por ID.")
	public void test_quandoNaoEncontraNFVendaPorID_lancaExcessao(){
		assertNotNull(this.buscaNotaFiscalService);
		var assertThrows = assertThrows(NotFoundException.class, ()->
			this.buscaNotaFiscalService.porId(3l));
		
		then(notaFiscalRepositoy).should(times(1)).findById(anyLong());	
		assertEquals(assertThrows.getMessage(), "Não encontrada a nf com id = 3");
		
	}
	
	
	@Test	
	@DisplayName("Testa Quando não encontra uma nota  por ID.")
	public void test_quandoEncontraNFVendaPorID_retornaNFVenda(){
		assertNotNull(this.buscaNotaFiscalService);
		
		NotaFiscalVendaEntity notaFiscalVendaEntityTreinado = new NotaFiscalVendaEntity();
		notaFiscalVendaEntityTreinado.setId(3l);
		
		when(notaFiscalRepositoy.findById(anyLong()))
				.thenReturn(Optional.of(notaFiscalVendaEntityTreinado));
		
		NotaFiscalVO notaFiscalVendaEntityRetornado = this.buscaNotaFiscalService.porId(3l);
		
		then(notaFiscalRepositoy).should(times(1)).findById(anyLong());
		
		assertNotNull(notaFiscalVendaEntityRetornado);
		assertEquals(3l, notaFiscalVendaEntityRetornado.getId());
		
		
	}

	
}
