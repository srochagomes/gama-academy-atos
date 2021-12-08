package net.atos.api.logistica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.ws.rs.NotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.repository.LogisticaRepository;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;



@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ConsultaOrdemServiceTest {

	private ConsultaOrdemService consultaService;	
	
	private LogisticaRepository logisticaRepository;
	
	private Pageable pageable;
	
	@BeforeEach
	public void iniciaCadaTeste() {

		this.logisticaRepository = Mockito.mock(LogisticaRepository.class);
		this.pageable = Mockito.mock(Pageable.class);	
		
		
		consultaService = new ConsultaOrdemService( 
				this.logisticaRepository); 
	}

	
	@Test
	public void test_quandoConsultaPeriodoOrdemServicoSemDados_lancarExcecao() {
		assertNotNull(consultaService);
		
		Page<OrdemServicoEntity> pageOrdemVazio = Mockito.mock(Page.class);
		when(pageOrdemVazio.isEmpty()).thenReturn(Boolean.TRUE);
		when(this.logisticaRepository.findByDataEmissaoBetween(any(), any(), any()))
		        .thenReturn(pageOrdemVazio);	
		 
		var excecao = assertThrows(NotFoundException.class, ()->consultaService.porPeriodo(LocalDate.now(),LocalDate.now(), this.pageable));
		
		assertNotNull(excecao);		
	}
	
	@Test
	public void test_quandoConsultaPeriodoOrdemServicoComDados_retornaVOs() {
		assertNotNull(consultaService);
		
		Stream<OrdemServicoEntity> entities = Stream.of(new OrdemServicoEntity(), new OrdemServicoEntity());
		
		Page<OrdemServicoEntity> pageOrdemComDados = Mockito.mock(Page.class);
		Pageable pageableMock  = Mockito.mock(Pageable.class);
		
		when(pageOrdemComDados.stream()).thenReturn(entities);
		
		when(pageOrdemComDados.getPageable()).thenReturn(pageableMock);
		
		when(this.logisticaRepository.findByDataEmissaoBetween(any(), any(), any()))
		        .thenReturn(pageOrdemComDados);	
		 
		Page<OrdemServicoVO> ordensEncontradas = consultaService.porPeriodo(LocalDate.now(),LocalDate.now(), this.pageable);
		
		
		assertNotNull(ordensEncontradas);
		assertEquals(2, ordensEncontradas.getContent().size());
		
		then(this.logisticaRepository).should(times(1)).findByDataEmissaoBetween(any(), any(), any());
		
	}
	

}
