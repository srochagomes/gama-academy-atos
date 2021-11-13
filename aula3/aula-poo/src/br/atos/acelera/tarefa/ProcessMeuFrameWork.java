package br.atos.acelera.tarefa;

public class ProcessMeuFrameWork {
	
	private final Operacao operacao;
	
	public ProcessMeuFrameWork(){
		operacao = new Locomocao();
	}
	
	
	public void processaOperacao(final Operacao op) {	
		
		this.operacao.nome = "dfsfs";
		
		op.executar();
	}
}
