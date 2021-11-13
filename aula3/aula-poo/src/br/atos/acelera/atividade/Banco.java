package br.atos.acelera.atividade;

import java.util.GregorianCalendar;

public class Banco {
	
	
	public void processarOperacoesBancarias(final Financa... financas ) {
		
		for (Financa financa : financas) {
			financa.depositar();
			financa.pagarBoletos();
			financa.sacar();	
			
		}		
	}
	

}
