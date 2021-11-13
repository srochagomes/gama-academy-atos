package br.atos.acelera.dados;

import br.atos.acelera.anotacao.AplicaSeguranca;
import br.atos.acelera.anotacao.Seguranca;

@AplicaSeguranca(tipoSeguranca = Seguranca.BASICA)
public class Aluno extends Pessoa{
	

	public Aluno() {
		super("");
	}
	
	
	void estudar() {
		
	}

	@AplicaSeguranca(tipoSeguranca = Seguranca.BASICA)	
	public void dormir() {
		System.out.println("Aluno dormindo 9hrs");
		super.dormir();
	}


			
	public void dormir(
			@AplicaSeguranca(tipoSeguranca = Seguranca.BASICA) 
								short tempo) {
		System.out.println("Aluno dormindo "+tempo+"hrs");
		super.dormir();	
		
	}
	


}
