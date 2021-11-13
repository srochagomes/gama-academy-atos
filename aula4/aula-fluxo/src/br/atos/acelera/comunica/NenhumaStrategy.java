package br.atos.acelera.comunica;

public class NenhumaStrategy implements ProcessoStrategy {

	@Override
	public void executar() {
		System.out.println("Não implementa Segurança");
	}

}
