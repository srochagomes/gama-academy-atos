package br.atos.acelera.comunica;

public class BasicaStrategy implements ProcessoStrategy{

	@Override
	public void executar() {
		Email email = new Email();
		email.iniciarEnvio();		
	}

}
