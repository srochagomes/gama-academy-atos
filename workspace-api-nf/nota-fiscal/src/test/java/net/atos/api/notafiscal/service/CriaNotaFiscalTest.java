package net.atos.api.notafiscal.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.ws.rs.BadRequestException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.atos.api.notafiscal.domain.Item;
import net.atos.api.notafiscal.domain.NotaFiscal;
import net.atos.api.notafiscal.domain.OperacaoFiscal;

@ExtendWith(MockitoExtension.class)
public class CriaNotaFiscalTest {
	
	
	private CriaNotaFiscal criaNotaFiscal;
	
	
	@BeforeEach
	public void iniciarCadaTeste() {
		criaNotaFiscal = new CriaNotaFiscal();	
	}
	
	@Test
	@DisplayName("Testa obrigatoriedade do campo Data de emissão.")
	void test_quando_dataEmissaoNull_LancarExcecao() {

		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
							criaNotaFiscal.persistir(notaFiscal));
		
		assertEquals("Campo data de emissão não pode ser nula", assertThrows.getMessage());
		
	}

	@Test
	@DisplayName("Testa obrigatoriedade do campo Data de lancamento.")
	public void test_quando_dataLancamentoNull_LancarExcecao() {

		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
							criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo data de Lançamento não pode ser nula", assertThrows.getMessage());
			
		
		
	}

	
	@Test
	@DisplayName("Testa obrigatoriedade do campo operação fiscal.")
	public void test_quando_operacaoFiscalNull_lancaExcecao() {

		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
							criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Operacao Fiscal não pode ser nulo", assertThrows.getMessage());

		
		
	}
	
	@Test
	@DisplayName("Testa obrigatoriedade do campo Valor.")
	public void test_quando_valorNull_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Valor não pode ser nulo", assertThrows.getMessage());
		
		
		
	}

	@Test
	@DisplayName("Testa obrigatoriedade do campo Documento.")
	public void test_quando_documentoNull_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Documento não pode ser nulo", assertThrows.getMessage());
		
	}
	
	@Test
	@DisplayName("Testa obrigatoriedade do campo itens é nulo.")
	public void test_quando_ItensNull_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		notaFiscal.setDocumento("1-91");

		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Itens deve ter pelo menos um item", assertThrows.getMessage());
		
	}
	
	@Test
	@DisplayName("Testa obrigatoriedade do campo itens ter no minimo um item .")
	public void test_quando_quantidadeIteMenorUm_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		notaFiscal.setDocumento("1-91");
		notaFiscal.setItens(new ArrayList<Item>());

		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Itens deve ter pelo menos um item", assertThrows.getMessage());
		
	}

	@Test
	@DisplayName("Testa obrigatoriedade do campo codigo produto quando tem itens.")
	public void test_quando_temUmItemEcodigoProdutoNulo_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		notaFiscal.setDocumento("1-91");
		
		Item item = new Item();
		notaFiscal.add(item);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Codigo produto não pode ser nulo", assertThrows.getMessage());
		
	}


	@Test
	@DisplayName("Testa obrigatoriedade do campo NCM quando tem itens.")
	public void test_quando_temUmItem_E_NCMNulo_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		notaFiscal.setDocumento("1-91");
		
		Item item = new Item();
		item.setCodigoProduto(1213);
		notaFiscal.add(item);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo NCM não pode ser nulo", assertThrows.getMessage());
		
	}

	
	@Test
	@DisplayName("Testa obrigatoriedade do campo Valor do Item quando tem itens.")
	public void test_quando_temUmItem_E_ValorItemNulo_lancaExcecao() {
		assertNotNull(criaNotaFiscal);

		NotaFiscal notaFiscal =  new NotaFiscal();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());
		notaFiscal.setOperacaoFiscal(OperacaoFiscal.VENDA);
		notaFiscal.setValor(BigDecimal.ZERO);
		notaFiscal.setDocumento("1-91");
		
		Item item = new Item();
		item.setCodigoProduto(1213);
		item.setNcm("AB-888888");
		notaFiscal.add(item);
		
		var assertThrows = assertThrows(BadRequestException.class, ()->
		criaNotaFiscal.persistir(notaFiscal));

		assertEquals("Campo Valor do Item não pode ser nulo", assertThrows.getMessage());
		
	}
	
}
