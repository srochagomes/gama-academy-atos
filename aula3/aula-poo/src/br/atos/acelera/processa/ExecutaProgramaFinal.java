package br.atos.acelera.processa;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.atos.acelera.dados.Aluno;
import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Pessoa;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaFinal {
	
	
	
	public static void main(String[] args) {
		
		Pessoa pessoa =  Pessoa.getInstance();
		Pessoa pessoa1 =  Pessoa.getInstance();
		pessoa.dormir();		
		pessoa.altura = 1.60f;		
		
		Aluno aluno = new Aluno();
		Aluno aluno2 = new Aluno();
		aluno.dormir();
		aluno.setNome("Bernardo");
		aluno.altura = 1.65f;
		
		Funcionario funcionario = new Funcionario("Carlos",(short)18, new Date());
		funcionario.dormir();
		funcionario.altura = 1.70f;
		
		Professor professor = ProfessorBuilder.iniciar()
									.comNome("Antonio")
									.comIdade((short)30)
									.comMateria("Matemática")
									.construir();
		
		professor.dormir();
		professor.setNome("Daniel");
		professor.altura = 1.80f;
		
		aluno.dormir((short)19);

		System.out.println("Pessoa = "+pessoa.getNome() + " " +pessoa.altura);
		System.out.println("Aluno = "+aluno.getNome() + " " +aluno.altura);
		System.out.println("Funcionario = "+funcionario.getNome() + " " +funcionario.altura);
		System.out.println("Professor = "+professor.getNome() + " " +professor.altura);
		
		pessoa = null;		
		pessoa.crescer();
		
	}

}
