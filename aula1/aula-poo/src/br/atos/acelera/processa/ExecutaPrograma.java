package br.atos.acelera.processa;

import br.atos.acelera.dados.Aluno;
import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Pessoa;
import br.atos.acelera.dados.Professor;

public class ExecutaPrograma{
	
	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		pessoa.dormir();
		
		Aluno aluno = new Aluno();
		aluno.dormir();
		
		Funcionario funcionario = new Funcionario();
		funcionario.dormir();
		
		Professor professor = new Professor();
		professor.dormir();
		
		aluno.dormir((short)19);		
		
	}

}
