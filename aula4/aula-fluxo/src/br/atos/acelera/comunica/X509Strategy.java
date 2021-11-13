package br.atos.acelera.comunica;

public class X509Strategy implements ProcessoStrategy {

	@Override
	public void executar() {
		ApiSoap soap = new ApiSoap();
		soap.iniciarEnvio();

	}

}
