package br.atos.acelera.comunica;

public class ApiRest extends Comunicacao {

	@Override
	protected void enviar() {
		System.out.println("Enviando ApiRest");

	}

	@Override
	public boolean precisaSeguranca() {
		return true;
	}
	
	@Override
	protected void configurarSeguranca() {
		System.out.println("Configurando Segurança com Oauth2");
	}

}
