package br.atos.acelera.atividade;

import java.util.GregorianCalendar;

import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Professor;

public class Banco {
	
	
	public void processarOperacoesBancarias(final Financa ... financas ) {		
		
		for (Financa financa : financas) {
			financa.depositar();
			financa.pagarBoletos();
			financa.sacar();	
			
		}		
	}
	

}
