package br.atos.acelera.processa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import br.atos.acelera.comparadores.ComparaProfessorPorNome;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaList {
	
	
	public static void main(String[] args) {		
		Collection<Professor> criaDados = criaDados();		
		
		Collections.sort((List<Professor>)criaDados,
				new ComparaProfessorPorNome());
		
		for (Professor professor: criaDados ) {
			System.out.println("Nome = "+ professor.getNome());
		}
		
	}
	
	public static Collection<Professor> criaDados() {
		Collection<Professor> professores = new ArrayList<Professor>();

		Professor professor = ProfessorBuilder
				.iniciar().comNome("Antonio")
				.comIdentificador(10)
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Bernadete")
				.comIdentificador(8)
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Jair")
				.comIdentificador(5)
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Raimundo")
				.comIdentificador(19)
				.construir();
		
		professores.add(professor);
		
		
		
		
		return professores;
		
	}

}
