package net.atos.api.notafiscal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public class NotaFiscalControllerIT {

	private static final String URI_NOTA_FISCAL = "/v1/notas-fiscais";
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
    private ObjectMapper mapper;
	
    private MockMvc mockMvc;
    
    @Autowired
    private EntityManager entityManager;
    
    @BeforeAll
    public void setup() {
    	
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        assertNotNull(this.entityManager);
        

    }
    
    
    @Test
    @DisplayName("Envio de nota fiscal sem os campos obrigatórios")
    public void test_envioCamposSemDados_retorna400() throws Exception {
    	NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isBadRequest());
    	
    }

    
    @Test    
    @DisplayName("Cria nota Fiscal de venda")
    public void test_criaNotaFiscalVenda_retornoCriado() throws Exception {
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
		
		item.setCodigoProduto(124);
		item.setNcm("AB-092893");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
    	
    	ResultActions resultCreated = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isCreated());
    	
    	NotaFiscalVO notaFiscalCriada = mapper.readValue(resultCreated
    						.andReturn()
    						.getResponse()
    						.getContentAsString(),
    						NotaFiscalVO.class);
    	
    	ResultActions resultConsulted = this.mockMvc.perform(
    			MockMvcRequestBuilders.get(URI_NOTA_FISCAL.concat("/{id}"),
    					notaFiscalCriada.getId()))
    					.andDo(print())
    					.andExpect(status().isOk());
    	
    	NotaFiscalVO notaFiscalConsultada = mapper.readValue(resultConsulted
				.andReturn()
				.getResponse()
				.getContentAsString(),
				NotaFiscalVO.class);
    	
    	assertEquals(2, notaFiscalConsultada.getItens().size());
    	assertEquals(OperacaoFiscalEnum.VENDA, notaFiscalConsultada.getOperacaoFiscal());
    }
    
    
    @Test    
    @DisplayName("Cria nota Fiscal de devolução sem id nota fiscal Venda")
    public void test_criaNotaFiscalDevolucaoSemIDVenda_retornoCriado() throws Exception {
    	NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());		
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.DEVOLUCAO);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("1-91");
		
		ItemVO item = new ItemVO();
		item.setCodigoProduto(123);
		item.setNcm("AB-092892");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
		
		item.setCodigoProduto(124);
		item.setNcm("AB-092893");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);

		ResultActions andExpect = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
    			.andExpect(status().isBadRequest());
		
		
		System.out.println(andExpect.andReturn().getRequest());
    	
    }

    @Test    
    @DisplayName("Cria nota Fiscal de devolução")
    public void test_criaNotaFiscalDevolucao_retornoCriado() throws Exception {
    	NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());		
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.VENDA);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("44441-91");
		
		ItemVO item = new ItemVO();
		item.setCodigoProduto(123);
		item.setNcm("AB-092892");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
		
		item.setCodigoProduto(124);
		item.setNcm("AB-092893");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
    	
    	ResultActions resultCreated = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isCreated());
    	
    	NotaFiscalVO notaFiscalCriada = mapper.readValue(resultCreated
    						.andReturn()
    						.getResponse()
    						.getContentAsString(),
    						NotaFiscalVO.class);
		
		
    	notaFiscalCriada.setOperacaoFiscal(OperacaoFiscalEnum.DEVOLUCAO);
    	notaFiscalCriada.setIdNotaFiscalVenda(notaFiscalCriada.getId());
    	notaFiscalCriada.setId(null);
    	
    	resultCreated = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscalCriada))
    			).andDo(print())
    			.andExpect(status().isCreated());
    	
    	NotaFiscalVO notaFiscalDevolvida = mapper.readValue(resultCreated
				.andReturn()
				.getResponse()
				.getContentAsString(),
				NotaFiscalVO.class);

    	ResultActions resultConsulted = this.mockMvc.perform(
    			MockMvcRequestBuilders.get(URI_NOTA_FISCAL.concat("/{id}"),
    					notaFiscalDevolvida.getId()))
    					.andDo(print())
    					.andExpect(status().isOk());
    	
    	NotaFiscalVO notaFiscalDevolvidaConsultada = mapper.readValue(resultConsulted
				.andReturn()
				.getResponse()
				.getContentAsString(),
				NotaFiscalVO.class);
    	
    	assertEquals(2, notaFiscalDevolvidaConsultada.getItens().size());
    	assertEquals(OperacaoFiscalEnum.DEVOLUCAO, notaFiscalDevolvidaConsultada.getOperacaoFiscal());
    	
    }
    
    @Test    
    @DisplayName("Tenta Criar nota Fiscal com Operacao Fiscal inexistente")
    public void test_criaNotaFiscalOperacaoFiscalIne_retornoBadRequest() throws Exception {
    	String notaFiscalString = 
    			"{\"id\":null,\"dataEmissao\":\"2021-11-30\","
    			+ "\"dataLancamento\":\"2021-11-30T18:54:14.625975\","
    			+ "\"operacaoFiscal\":\"TRANSFERENCIA_INTERNA\",\"valor\":1,"
    			+ "\"documento\":\"1-91\",\"itens\":"
    			+ "[{\"codigoProduto\":124,\"ncm\":\"AB-092893\","
    			+ "\"valor\":1},{\"codigoProduto\":124,"
    			+ "\"ncm\":\"AB-092893\",\"valor\":1}],\"idNotaFiscalVenda\":null}";
    	
    	ResultActions resultCreated = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(notaFiscalString)
    			).andDo(print())
    			.andExpect(status().isBadRequest());

    }
    
    @Test    
    @DisplayName("Tenta Criar nota Fiscal de Transferencia que não tem implementacao")
    public void test_criaNotaFiscalOperacaoFiscalNaoImplementada_retornoBadRequest() throws Exception {
    	NotaFiscalVO notaFiscal =  new NotaFiscalVO();
		notaFiscal.setDataEmissao(LocalDate.now());		
		notaFiscal.setDataLancamento(LocalDateTime.now());		
		notaFiscal.setOperacaoFiscal(OperacaoFiscalEnum.TRAFERENCIA);
		notaFiscal.setValor(BigDecimal.ONE);
		notaFiscal.setDocumento("1-91");
		
		ItemVO item = new ItemVO();
		item.setCodigoProduto(123);
		item.setNcm("AB-092892");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
		
		item.setCodigoProduto(124);
		item.setNcm("AB-092893");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isBadRequest());
    }
    
    
    @Test    
    @DisplayName("Cria nota Fiscal de venda e cancela")
    public void test_criaNotaFiscalECancela_retornoCriado() throws Exception {
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
		
		item.setCodigoProduto(124);
		item.setNcm("AB-092893");
		item.setValor(BigDecimal.ONE);
		notaFiscal.add(item);
    	
    	ResultActions resultCreated = this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isCreated());
    	
    	NotaFiscalVO notaFiscalCriada = mapper.readValue(resultCreated
    						.andReturn()
    						.getResponse()
    						.getContentAsString(),
    						NotaFiscalVO.class);
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders.patch(URI_NOTA_FISCAL.concat("/{id}/cancelamento"),
    					notaFiscalCriada.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isOk());
    }
    

    @Test    
    @DisplayName("Consulta nota Fiscal de venda por documento")
    public void test_criaNotaFiscalPorDocumento_retornoOK() throws Exception {
    	ResultActions resultConsulted = this.mockMvc.perform(
    			MockMvcRequestBuilders.get(URI_NOTA_FISCAL.concat("/documentos/{documento}"),
    					"1-91"))
    					.andDo(print())
    					.andExpect(status().isOk());	
    	
    	
    	List<NotaFiscalVO> notasFiscaisConsultadas = mapper.readValue(resultConsulted
				.andReturn()
				.getResponse()
				.getContentAsString(),
				new TypeReference<List<NotaFiscalVO>>() { });
    	
    	System.out.println("(Consulta pot documento) Quantidade de notas do documento 1-91 = "+notasFiscaisConsultadas.size());
    	assertNotNull(notasFiscaisConsultadas);
    }

    
    @Test    
    @DisplayName("Consulta nota Fiscal de venda por periodo")
    public void test_criaNotaFiscalPorPeriodo_retornoOK() throws Exception {
    	String dataInicio = LocalDate.now().minusDays(1l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	String dataFim = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	   	
    	
    	ResultActions resultConsulted = this.mockMvc.perform(
    			MockMvcRequestBuilders.get(URI_NOTA_FISCAL.concat("/emissao-periodos/{dataInicio}/{dataFim}"),
    					dataInicio,dataFim))
    					.andDo(print())
    					.andExpect(status().isOk());	
    	
    	
    	List<NotaFiscalVO> notasFiscaisConsultadas = mapper.readValue(resultConsulted
				.andReturn()
				.getResponse()
				.getContentAsString(),
				new TypeReference<List<NotaFiscalVO>>() { });
    	
    	System.out.println("(Consulta pot periodo) Quantidade de notas do documento 1-91 = "+notasFiscaisConsultadas.size());
    	assertNotNull(notasFiscaisConsultadas);
    }
    
    

}
