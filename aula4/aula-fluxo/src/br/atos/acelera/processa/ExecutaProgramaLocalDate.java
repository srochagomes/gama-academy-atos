package br.atos.acelera.processa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ExecutaProgramaLocalDate {
	
	
	public static void main(String[] args) {
		LocalDate dataAtual = LocalDate.now();
		System.out.println(dataAtual);
		
		
		LocalTime horaAtual = LocalTime.now();
		System.out.println(horaAtual);
		
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		System.out.println(dataHoraAtual);
		
		LocalDate dataFormatada = LocalDate
				.parse("04/10/2021", 
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		System.out.println(dataFormatada);
		System.out.println(dataFormatada.format(DateTimeFormatter
												.ofPattern("dd/MM/yyyy")));
		
		
	}

}
