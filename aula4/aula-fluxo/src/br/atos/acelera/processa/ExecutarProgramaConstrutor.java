package br.atos.acelera.processa;

import br.atos.acelera.dados.Pessoa;

public class ExecutarProgramaConstrutor extends Pessoa{
	
	public ExecutarProgramaConstrutor(){
		super("teste");		
	}
	
	public static void main(String[] args) {
		
		Pessoa pessoa = new ExecutarProgramaConstrutor();
		
		pessoa.dormir();
	}

}
