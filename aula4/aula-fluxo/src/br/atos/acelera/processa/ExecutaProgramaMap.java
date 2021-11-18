package br.atos.acelera.processa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.atos.acelera.comparadores.ComparaProfessorPorNome;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaMap {
	
	
	public static void main(String[] args) {		
		Map<Integer,Professor> criaDados = criaDados();		
		
		
		
		for (Map.Entry<Integer,Professor> item: criaDados.entrySet()) {
			System.out.println("key = "+ item.getKey() + "  value = "+item.getValue());
		}
	
		Professor professorEncontrado = criaDados.get(19);
		
		System.out.println("objeto encontrado = "+professorEncontrado);
	}
	
	public static Map<Integer, Professor> criaDados() {
		Map<Integer, Professor> professores = new HashMap<>();

		Professor professor = ProfessorBuilder
				.iniciar().comNome("Antonio")
				.comIdentificador(10)
				.construir();
		
		professores.put(10,professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Bernadete")
				.comIdentificador(19)
				.construir();
		
		professores.put(19,professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Jair")
				.comIdentificador(5)
				.construir();
		
		professores.put(5,professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Raimundo")
				.comIdentificador(19)
				.construir();
		
		professores.put(19,professor);
		
		
		
		
		return professores;
		
	}

}
