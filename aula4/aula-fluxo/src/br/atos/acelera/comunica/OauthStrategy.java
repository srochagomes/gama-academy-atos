package br.atos.acelera.comunica;

public class OauthStrategy implements ProcessoStrategy {

	@Override
	public void executar() {
		ApiRest api = new ApiRest();
		api.iniciarEnvio();
	}

}
