package br.atos.acelera.processa;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.atos.acelera.atividade.Banco;
import br.atos.acelera.atividade.Financa;
import br.atos.acelera.dados.Aluno;
import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Pessoa;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;

public class ExecutaProgramaVarArgs {
	
	
	
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		Pessoa pessoa = Pessoa.getInstance();
		Funcionario funcionario = new Funcionario("Jorge", (short)25, new Date());
		Professor professor = ProfessorBuilder.iniciar()
				.comNome("Elisangela")
				.comNascimento(new Date())
				.comIdade((short)13)
				.construir();
				
		
		banco.processarOperacoesBancarias(funcionario, professor);	
		
		
	}

}
