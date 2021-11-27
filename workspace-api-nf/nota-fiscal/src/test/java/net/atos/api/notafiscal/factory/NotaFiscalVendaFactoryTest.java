package net.atos.api.notafiscal.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class NotaFiscalVendaFactoryTest {

	@Test
	@DisplayName("Testa o factory de VO para entity")
	public void test_quandoCriarEntityPeloVO_sohTransformaparaEntity() {
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

		NotaFiscalVendaEntity entityCriada = 
				new NotaFiscalVendaFactory(notaFiscal).toEntity();
		
		assertNotNull(entityCriada);
		assertNotNull(entityCriada.getDataEmissao());
		assertEquals(notaFiscal.getDataEmissao(),entityCriada.getDataEmissao());
		
		assertNotNull(entityCriada.getDataLancamento());
		assertEquals(notaFiscal.getDataLancamento(),entityCriada.getDataLancamento());
		
		assertNotNull(entityCriada.getDocumento());
		assertEquals(notaFiscal.getDocumento(),entityCriada.getDocumento());
		

		assertNotNull(entityCriada.getOperacaoFiscal());
		assertEquals(notaFiscal.getOperacaoFiscal(),entityCriada.getOperacaoFiscal());
		
		assertNotNull(entityCriada.getValor());
		assertEquals(notaFiscal.getValor(),entityCriada.getValor());
		
		assertNotNull(entityCriada.getItens());
		assertEquals(notaFiscal.getItens().size(),entityCriada.getItens().size());
		assertEquals(notaFiscal.getItens().get(0).getCodigoProduto(),entityCriada.getItens().get(0).getCodigoProduto());
		assertEquals(notaFiscal.getItens().get(0).getNcm(),entityCriada.getItens().get(0).getNcm());
		assertEquals(notaFiscal.getItens().get(0).getValor(),entityCriada.getItens().get(0).getValor());
		
		NotaFiscalVO voCriado = 
				new NotaFiscalVendaFactory(entityCriada).toVO();

		assertNotNull(voCriado);
		assertNotNull(voCriado.getDataEmissao());
		assertEquals(notaFiscal.getDataEmissao(),voCriado.getDataEmissao());
		
		assertNotNull(voCriado.getDataLancamento());
		assertEquals(notaFiscal.getDataLancamento(),voCriado.getDataLancamento());
		
		assertNotNull(voCriado.getDocumento());
		assertEquals(notaFiscal.getDocumento(),voCriado.getDocumento());
		

		assertNotNull(voCriado.getOperacaoFiscal());
		assertEquals(notaFiscal.getOperacaoFiscal(),voCriado.getOperacaoFiscal());
		
		assertNotNull(voCriado.getValor());
		assertEquals(notaFiscal.getValor(),voCriado.getValor());
		
		assertNotNull(voCriado.getItens());
		assertEquals(notaFiscal.getItens().size(),voCriado.getItens().size());
		assertEquals(notaFiscal.getItens().get(0).getCodigoProduto(),voCriado.getItens().get(0).getCodigoProduto());
		assertEquals(notaFiscal.getItens().get(0).getNcm(),voCriado.getItens().get(0).getNcm());
		assertEquals(notaFiscal.getItens().get(0).getValor(),voCriado.getItens().get(0).getValor());
		
	}

}
