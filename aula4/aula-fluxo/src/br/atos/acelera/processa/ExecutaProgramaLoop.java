package br.atos.acelera.processa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaLoop {
	
	
	public static void main(String[] args) {
		
		//whileMetodo();
		//forEachMetodo();
		//doWhileMetodo();
		forInicialMetodo();
		
			
	}
	
	private static void forEachMetodo() {
		List<Professor> professores = new ArrayList<>();
		
		professores.add(ProfessorBuilder.iniciar()
				.comNome("Antonio").construir());
		
		professores.add(ProfessorBuilder.iniciar()
				.comNome("Marcia").construir());
		
		professores.add(ProfessorBuilder.iniciar()
				.comNome("Carla").construir());
		
		for (Professor prof : professores) {
			System.out.println("Nome do professor = "
							+prof.getNome());
			
		}
		
		 
		
	}
	
	private static void forInicialMetodo() {
		List<Professor> professores = new ArrayList<>();
		
		
		for(int indiceFora = 0; indiceFora < 2; indiceFora++ ) {
			for (int indice = 0 ; 
					indice < 10 ; 
					indice++) {
				
				
				if (indice%2!=0) {
					continue ;
				}
				
				System.out.println("For com indice = "+indice);
				
			}
			
		}
		
	}
	
	
	private static void forLabelInicialMetodo() {
		List<Professor> professores = new ArrayList<>();
		
		FOR_1:
		for(int indiceFora = 0; indiceFora < 2; indiceFora++ ) {
			FOR_2:
			for (int indice = 0 ; 
					indice < 10 ; 
					indice++) {
				
				
				if (indice%2!=0) {
					continue FOR_1;
				}
				
				System.out.println("For com indice = "+indice);
				
			}
			
		}
		
	}

	private static void whileMetodo() {
		Professor professor = ProfessorBuilder
				.iniciar()
				.comIdentificador(30)
				.comQtDiasTrabalhados(0)
				.comNome("Antonio Olimpio")
				.comSalario(3000.0)
				.comIdade((short)35).construir();

		while(professor.deveTrabalhar()) {
			
			System.out.println("Professor deve trabalhar");
			
			if (professor.getQtDiasTrabalhados() == 50) {
				break;
			}
			
			professor.adicionaDiasTrabalhados(10);			
			
			
		}
		
		System.out.println("Professor deve sair de férias");
	}

	private static void doWhileMetodo() {
		Professor professor = ProfessorBuilder
				.iniciar()
				.comIdentificador(30)
				.comQtDiasTrabalhados(0)
				.comNome("Antonio Olimpio")
				.comSalario(3000.0)
				.comIdade((short)35).construir();

		do {
			
			System.out.println("Professor deve trabalhar");
			
			if (professor.getQtDiasTrabalhados() == 50) {
				break;
			}
			
			professor.adicionaDiasTrabalhados(10);			
			
			
		}while(professor.deveTrabalhar()); 
		
		System.out.println("Professor deve sair de férias");
	}
	
}
