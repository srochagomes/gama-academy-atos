package br.atos.acelera.comunica;

public class ApiSoap extends Comunicacao {

	@Override
	public void enviar() {
		System.out.println("Enviando ApiSoap");

	}

	@Override
	public boolean precisaSeguranca() {
		return true;
	}

}
