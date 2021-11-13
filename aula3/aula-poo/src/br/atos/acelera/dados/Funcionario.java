package br.atos.acelera.dados;

import java.util.Date;

import br.atos.acelera.atividade.Financa;
import br.atos.acelera.atividade.Financa2;

public class Funcionario extends Pessoa implements Financa, Financa2 {
	
	private long numeroMatricula;
	

	
	public Funcionario(String aNome,short aIdade, Date aNascimento) {
		super(aNome);
		this.setIdade(aIdade);
		this.setNascimento(aNascimento);
		
	}

	@Override
	public void pagarBoletos() {
		System.out.println(this.getNome().concat(" está Pagando Boleto"));
		
	}

	@Override
	public void depositar() {
		System.out.println(this.getNome().concat(" está Depositando salário"));
		
	}

	@Override
	public void sacar() {
		System.out.println(this.getNome().concat(" está Sacando salário"));
		
	}
	
	
	
}
