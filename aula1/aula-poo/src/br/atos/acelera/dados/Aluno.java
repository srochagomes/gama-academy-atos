package br.atos.acelera.dados;

public class Aluno extends Pessoa{
	
	void estudar() {
		
	}

	@Override
	public void dormir() {
		System.out.println("Aluno dormindo 9hrs");
		super.dormir();		
	}


		
	public void dormir(short tempo) {
		System.out.println("Aluno dormindo "+tempo+"hrs");
		super.dormir();		
	}


}
