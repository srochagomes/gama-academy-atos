package br.atos.acelera.processa;

import br.atos.acelera.anotacao.Seguranca;
import br.atos.acelera.comunica.ApiRest;
import br.atos.acelera.comunica.ApiSoap;
import br.atos.acelera.comunica.Email;
import br.atos.acelera.comunica.ProcessoStrategy;

public class ExecutaProcessaStrategy {
	
	public static void main(String[] args) {
		String nomeIntegracao = "oauth";
		Seguranca seguranca = Seguranca.parse(nomeIntegracao);
		
		ProcessoStrategy starategy = seguranca.getProcesso();
		starategy.executar();		
		
				
//		if (seguranca.equals(seguranca.BASICA)) {
//			Email email = new Email();
//			email.iniciarEnvio();
//			
//		}else if (seguranca.equals(seguranca.OAUTH)) {
//			ApiRest api = new ApiRest();
//			api.iniciarEnvio();
//			
//		}else if (seguranca.equals(seguranca.X509)) {
//			ApiSoap soap = new ApiSoap();
//			soap.iniciarEnvio();			
//		} else {
//			System.out.println("Não implementa Segurança");
//		}
		
	}

}
