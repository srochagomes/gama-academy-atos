package net.atos.api.notafiscal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class NotaFiscalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotaFiscalApplication.class, args);
	}

}
