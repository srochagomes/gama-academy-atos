package br.com.maven;

import org.apache.commons.lang3.ObjectUtils;


public class ProcessarLibExterna {

	public static void main(String[] args) {
		String valor1 = null;
		String valor2 = null;
		String valor3 = null;
		String valor4 = null;
		
		
		System.out.println("Tem Nulo ente os valores ="+
				ObjectUtils.anyNull(valor1,valor2,valor3, valor4));
		
		System.out.println("Todos são nulos ="+
				ObjectUtils.allNull(valor1,valor2,valor3, valor4));
		
		System.out.println("Todos tem valores ="+
				ObjectUtils.allNotNull(valor1,valor2,valor3, valor4));
		
		
	}

}
