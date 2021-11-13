package br.atos.acelera.processa;

import java.util.Date;

import br.atos.acelera.atividade.Banco;
import br.atos.acelera.dados.Funcionario;
import br.atos.acelera.dados.Pessoa;
import br.atos.acelera.dados.Professor;
import br.atos.acelera.dados.Professor.ProfessorBuilder;
import br.atos.acelera.tarefa.Alimentacao;
import br.atos.acelera.tarefa.Locomocao;
import br.atos.acelera.tarefa.Operacao;
import br.atos.acelera.tarefa.ProcessMeuFrameWork;

public class ExecutaProgramaInterface {
	
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		Pessoa pessoa = Pessoa.getInstance();
		Funcionario funcionario = new Funcionario("Jorge", (short)25, new Date());
		Professor professor = ProfessorBuilder.iniciar()
				.comNome("Elisangela")
				.comNascimento(new Date())
				.comIdade((short)13)
				.construir();
				
		
		banco.processarOperacoesBancarias(funcionario);
		banco.processarOperacoesBancarias(professor);
		
	}

}
