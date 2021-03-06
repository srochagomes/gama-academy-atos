package br.atos.acelera.comunica;

public abstract  class Comunicacao {
	
	public void iniciarEnvio() {
		
		if (this.precisaSeguranca()) {			
			this.configurarSeguranca();
		}
		
		this.enviar();
		
		
	}
	
	
	protected abstract void enviar();
	
	public abstract boolean precisaSeguranca();

	protected void configurarSeguranca() {
		System.out.println("Configurando Segurança default");
	}
}
