package br.atos.acelera.abstr_interf;

public class ProcessaClasseConcreta {
	
	public static void main(String[] args) {
		ClasseConcreta concreta = new ClasseConcreta();
		ClasseAbstrataA abstrataA = concreta;
		ClasseAbstrataB abstrataB = concreta;
		InterfaceA interfaceA = concreta;
		InterfaceB interfaceB = concreta;
		
		abstrataA.imprimirA();
		abstrataB.imprimirB();
		
		interfaceA.imprimirA();
		interfaceB.imprimirB();
		
		
		
	}

}
