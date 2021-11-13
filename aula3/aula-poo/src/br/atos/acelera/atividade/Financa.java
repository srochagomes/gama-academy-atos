package br.atos.acelera.atividade;

public interface Financa {
	
	Integer numeroResidencia = 9;
	
	void pagarBoletos();
	void depositar();
	void sacar();
	
	default void sacarDinheiro() {
		System.out.println("Teste");
	}
	
	default void sacarDinheiro2() {
		System.out.println("Teste");
	}

}
