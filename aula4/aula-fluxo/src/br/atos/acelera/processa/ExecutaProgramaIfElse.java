package br.atos.acelera.processa;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaIfElse {
	
	public static void main(String[] args) {
		Professor professor = ProfessorBuilder
				.iniciar()
				.comSalario(3000.0)
				.comIdade((short)35).construir();
				
		//&
		//|
		//&&
		//||
		if (professor.getIdade()>30
				&& professor.getSalario() < 4000.0) {
			
			System.out.println("Professor tem bonus");
		}		
		
		System.out.println("Professor receber salario");
		
		int idade1 = 25;
		
		int idade2 = 26;
		
		boolean idadesDiferentes = false;
		
		if (idadesDiferentes  = (idade1 != idade2)) {
			System.out.println("Idades são Diferentes");
		}else {
			System.out.println("Idades são Iguais");
		}
		
		
		
		
		Professor professor1 = ProfessorBuilder
				.iniciar()
				.comIdentificador(3)
				.comNome("Antonio Olimpio")
				.comSalario(3000.0)
				.comIdade((short)35).construir();
		
		Professor professorEqual = professor1;  
		
		Professor professor2 = ProfessorBuilder
				.iniciar()
				.comIdentificador(3)
				.comNome("Antonio Bustos")
				.comSalario(7000.0)
				.comIdade((short)35).construir();
 
		if (professor1.equals(professorEqual)) {
			System.out.println("Professores são Iguais");
		}else if (professor1.getSalario()>3000) {
			System.out.println("");
		}else {
			System.out.println("");
		}

		//////
		
		if (professor1.equals(professorEqual)) {
			System.out.println("Professores são Iguais");
		}
		
		if (professor1.getSalario()>3000) {
			System.out.println("");
		}
		
		
		/////
		
		if (professor1.temBonus()) {
			System.out.println("Professores tem bonus");
		}
		
		
		
		
		System.out.println("");
				
	}

}
