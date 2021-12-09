package net.atos.api.logistica.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogisticaControllerIT {


	private static final String URI_ORDEM_SERVICO = "/v1/ordens-servicos";
	
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
    	String dataInicio = LocalDate.now().minusDays(1l).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	String dataFim = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	
    	
    	this.mockMvc.perform(
    			MockMvcRequestBuilders
    			.get(URI_ORDEM_SERVICO
    					.concat("/periodo-inicial/{inicio}/periodo-final/{fim}"),
    					dataInicio,dataFim))
    					.andDo(print())
    					.andExpect(status().isNotFound());
    	
    }

	
}
