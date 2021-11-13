package br.atos.acelera.comunica;

public class Sms extends Comunicacao {

	@Override
	public void enviar() {
		System.out.println("Enviando Sms");

	}

	@Override
	public boolean precisaSeguranca() {		
		return false;
	}

	@Override
	public void configurarSeguranca() {
		// TODO Auto-generated method stub
		
	}

}
