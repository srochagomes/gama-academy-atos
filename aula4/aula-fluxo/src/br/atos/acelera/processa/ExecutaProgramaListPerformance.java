package br.atos.acelera.processa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.atos.acelera.dados.Pessoa;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaListPerformance {
	
	
	public static void main(String[] args) {		
		Collection<Professor> professores = criaDados();		
		
		for (Professor professor: professores ) {
			System.out.println("Identificador = "+ professor.getIdentificador());
			Pessoa p = professor;
			
		}
		
			
		
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		System.out.println("Inicio ="+dataHoraAtual.format(DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));
		
		Professor search = 
				ProfessorBuilder.iniciar()
				.comIdentificador(499990)
				.construir();
		
		buscaProfessor((List<Professor>)professores, search);
		
		
		LocalDateTime dataHoraFinal = LocalDateTime.now();
		System.out.println("Fim = "+dataHoraFinal.format(DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")));
		
		
		
	}
	
	public static Professor buscaProfessor(List<Professor> professores, Professor professorSearch) {
		
		
		int indexOf = Collections.binarySearch(professores, professorSearch);	
		
		if (indexOf < 0) {
			return null;
		}
		
		return professores.get(indexOf);
		
	}
	
public static Professor buscaProfessor(Set<Professor> professores, Professor professorSearch) {
		
		
		for (Professor professorLoop: professores  ) {
			if (professorLoop.equals(professorSearch)) {
				return professorLoop;
			}
		}
		
		return null;
	}


	
	public static Collection<Professor> criaDados() {
		Collection<Professor> professores = new ArrayList<Professor>();

		for(int i = 0 ; i < 5000000; i++) {
			professores.add(ProfessorBuilder
					.iniciar()
					.comIdentificador(i)
					.comNome("Professor"+i)
					.construir());	
		}		
		
		return professores;
		
	}
	
	public static boolean existeProfessor(Set<Professor> professores, Professor professorSearch) {
		return professores.contains(professorSearch);	
	}
	
}
