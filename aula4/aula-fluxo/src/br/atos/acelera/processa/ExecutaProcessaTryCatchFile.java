package br.atos.acelera.processa;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.atos.acelera.dados.Pessoa;

public class ExecutaProcessaTryCatchFile {
	
	
	public static void main(String[] args) {
		
		InputStream inputStream = Pessoa.class.getResourceAsStream("/item.csv");
		

	        
	        try(BufferedReader br
	      	      = new BufferedReader(new InputStreamReader(
	    	    		  inputStream))) {	        	
	        	br.lines().forEach(System.out::println);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
}
