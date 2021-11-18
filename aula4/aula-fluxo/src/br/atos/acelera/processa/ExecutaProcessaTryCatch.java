package br.atos.acelera.processa;

import br.atos.acelera.exceptions.AulaExceptionChecked;
import br.atos.acelera.exceptions.AulaExceptionUnChecked;

public class ExecutaProcessaTryCatch {
	
	
	public static void main(String[] args) throws AulaExceptionChecked {
		System.out.println("Iniciou o processo");
		try {
			System.out.println("Resultado  do calculo "+
			 dividirValores(10, 1));
		} catch (Exception e) {
			throw new AulaExceptionChecked("Erro ao tentar dividir por zero",e);
		}
		somarValores(0, 0);
		System.out.println("Finalizou o processo");
	}
	
	public static Integer dividirValores(Integer num1, Integer num2) 
			throws Exception{
		
		return num1 / num2;
	}
	
	public static Integer somarValores(Integer num1, Integer num2) throws AulaExceptionUnChecked{
		if (num1 == 0 && num2 == 0) {
			throw new AulaExceptionUnChecked("Deve ser informadop pelo menos um numero");
		}
		
		return num1 / num2;
	}
	
}
