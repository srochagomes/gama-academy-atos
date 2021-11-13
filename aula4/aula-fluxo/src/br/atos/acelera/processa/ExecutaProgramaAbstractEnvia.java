package br.atos.acelera.processa;

import java.util.Arrays;
import java.util.List;

import br.atos.acelera.comunica.ApiRest;
import br.atos.acelera.comunica.ApiSoap;
import br.atos.acelera.comunica.Comunicacao;
import br.atos.acelera.comunica.Email;
import br.atos.acelera.comunica.MaquinaIntegradora;
import br.atos.acelera.comunica.Sms;

public class ExecutaProgramaAbstractEnvia {
	
	public static void main(String[] args) {
		MaquinaIntegradora maquina = new MaquinaIntegradora();
		
		List<Comunicacao> comunicacoes = Arrays.asList(new Email(), new Sms(), new ApiRest(), new ApiSoap()); 
		
		comunicacoes.stream().forEach(comunicao-> maquina.processar(comunicao));
		
	}

}
