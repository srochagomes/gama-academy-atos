package net.atos.api.notafiscal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

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

import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)

public class BuscaNotaFiscalVendaServiceTest {

	private BuscaNotaFiscalVendaService buscaNotaFiscal;
	
	private Validator validator;
	
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	
	
	
	@BeforeAll
	public void inicioGeral() {
		ValidatorFactory validatorFactor = 
				Validation.buildDefaultValidatorFactory();
		
		this.validator = validatorFactor.getValidator();	
	}
	
	@BeforeEach
	public void iniciarCadaTeste() {
		
		this.notaFiscalRepositoy = Mockito.mock(NotaFiscalVendaRepository.class);
		
		buscaNotaFiscal = new BuscaNotaFiscalVendaService(validator, notaFiscalRepositoy);	
	}

	@Test	
	@DisplayName("Testa Quando não encontra uma nota de devolucao por ID.")
	public void test_quandoNaoEncontraNFVendaPorID_lancaExcessao(){
		assertNotNull(buscaNotaFiscal);
		var assertThrows = assertThrows(NotFoundException.class, ()->
			buscaNotaFiscal.porId(3l));
		
		then(notaFiscalRepositoy).should(times(1)).findById(anyLong());	
		assertEquals(assertThrows.getMessage(), "Não encontrada a nf de venda com id = 3");
		
		
		
		
		
	}
	
	
	@Test	
	@DisplayName("Testa Quando não encontra uma nota de devolucao por ID.")
	public void test_quandoEncontraNFVendaPorID_retornaNFVenda(){
		assertNotNull(buscaNotaFiscal);
		
		NotaFiscalVendaEntity notaFiscalVendaEntityTreinado = new NotaFiscalVendaEntity();
		notaFiscalVendaEntityTreinado.setId(3l);
		
		when(notaFiscalRepositoy.findById(anyLong()))
				.thenReturn(Optional.of(notaFiscalVendaEntityTreinado));
		
		NotaFiscalVendaEntity notaFiscalVendaEntityRetornado = buscaNotaFiscal.porId(3l);
		
		then(notaFiscalRepositoy).should(times(1)).findById(anyLong());
		
		assertNotNull(notaFiscalVendaEntityRetornado);
		assertEquals(3l, notaFiscalVendaEntityRetornado.getId());
		
		
	}
	
}
