package br.atos.acelera.processa;

import br.atos.acelera.tarefa.Alimentacao;
import br.atos.acelera.tarefa.Locomocao;
import br.atos.acelera.tarefa.Operacao;
import br.atos.acelera.tarefa.ProcessMeuFrameWork;

public class ExecutaProgramaAbstract {
	
	public static void main(String[] args) {
		ProcessMeuFrameWork processMeuFrameWork = new ProcessMeuFrameWork();
		
		
		Operacao operacao = new Alimentacao();
		
		processMeuFrameWork.processaOperacao(operacao);
	}

}
