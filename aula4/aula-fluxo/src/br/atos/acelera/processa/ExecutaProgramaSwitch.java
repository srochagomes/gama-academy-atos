package br.atos.acelera.processa;

import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaSwitch {
	
	
	public static void main(String... args) {
		
		Professor professor1 = ProfessorBuilder
				.iniciar()
				.comIdentificador(30)
				.comNome("Antonio Olimpio")
				.comSalario(3000.0)
				.comIdade((short)35).construir();
		
		
		switch (professor1.getIdentificador()) {
			case 1:
				System.out.println("Nivel inicial");		
				//break;	
			default:
				System.out.println("Nivel Default");
				//break;
			case 2:
				System.out.println("Nivel medio");		
				break;
			case 3:
				System.out.println("Nivel Avançado");		
				//break;
				
			
			}

	}

}
