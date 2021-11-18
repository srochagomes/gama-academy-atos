package br.atos.acelera.processa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.atos.acelera.comparadores.ComparaProfessorPorNome;
import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaStream {
	
	
	public static void main(String[] args) {		
		Collection<Professor> professores = criaDados();		
		
		Collections.sort((List<Professor>)professores,
				new ComparaProfessorPorNome());

		AtomicInteger index = new AtomicInteger();
//		professores.stream()
//			.forEach(qualquerdados -> {
//				System.out.println(qualquerdados.getNome());
//				System.out.println(index.getAndIncrement());
//				});
		
//		professores.parallelStream()
//		.forEach(System.out::println);
//	
//		professores.stream()
//	    .filter(Professor::ehDeMaiorque25)
//	    .forEach(System.out::println);
//
//		
//		professores.stream()
//		    .filter(item -> item.ehDeMaiorque(Short.parseShort("20")))
//		    .forEach(System.out::println);
		
		
//		professores.stream().map(professor -> 
//		 	new Funcionario(professor.getNome(), 
//		 			professor.getIdade(), professor.getNascimento()))
//		 	.forEach(System.out::println);

		List<Integer> collect = professores.stream()
									.map(Professor::getIdentificador)
									.collect(Collectors.toList());
	 	
		
	}
	
	private static void collect(Collector<Object, ?, List<Object>> list) {
		// TODO Auto-generated method stub
		
	}

	public static Collection<Professor> criaDados() {
		Collection<Professor> professores = new ArrayList<Professor>();

		Professor professor = ProfessorBuilder
				.iniciar().comNome("Antonio")
				.comIdentificador(10)
				.comIdade(Short.parseShort("48"))
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Bernadete")
				.comIdentificador(Integer.parseInt("13"))
				.comIdade(Short.parseShort("15"))
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Jair")
				.comIdentificador(5)
				.comIdade(Short.parseShort("20"))
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Raimundo")
				.comIdentificador(19)
				.comIdade(Short.parseShort("30"))
				.construir();
		
		professores.add(professor);
		
		professor = ProfessorBuilder
				.iniciar().comNome("Marcia")
				.comIdentificador(29)
				.comIdade(Short.parseShort("27"))
				.construir();
		
		professores.add(professor);
		
		
		
		return professores;
		
	}

}
