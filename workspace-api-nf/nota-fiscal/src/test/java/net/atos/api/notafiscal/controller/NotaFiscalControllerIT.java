package net.atos.api.notafiscal.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    
    @BeforeAll
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

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
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders.post(URI_NOTA_FISCAL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(notaFiscal))
    			).andDo(print())
    			.andExpect(status().isCreated());
    }



}
