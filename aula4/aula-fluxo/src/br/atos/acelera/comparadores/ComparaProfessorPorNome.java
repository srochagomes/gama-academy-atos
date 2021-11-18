package br.atos.acelera.comparadores;

import java.util.Comparator;

import br.atos.acelera.dados.Professor;

public class ComparaProfessorPorNome implements Comparator<Professor>{

	@Override
	public int compare(Professor primeiro, Professor segundo) {
		return primeiro.getNome().compareTo(segundo.getNome());
	}


}
