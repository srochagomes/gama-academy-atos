package br.atos.acelera.processa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutaProgramaData {
	
	public static void main(String[] args) {
		
		Date data = new Date();
		
		data.setDate(data.getDate()+30);
		
		
		System.out.println("Data = "+data);
		System.out.println("Data = "+data.getDate());
		System.out.println("Dia = "+ data.getDay());
		System.out.println("Mês = "+data.getMonth());
		System.out.println("Ano = "+data.getYear());
		System.out.println("Hora = "+data.getHours());
		System.out.println("Minutos = "+data.getMinutes());
		System.out.println("Segundos = "+data.getSeconds());
		System.out.println("TimeStamp = "+data.getTime());
		System.out.println("Data = "+data.toGMTString());
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YY HH:mm:ss");
		String dataFormatada = format.format(data);
		System.out.println("Data formatada= "+dataFormatada);
		
		try {
			format = new SimpleDateFormat("dd/MM/Y");
			Date parse = format.parse("03/12/1983");
			data = parse;
			//data = new Date("03/12/1983");			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("Data = "+data);
		System.out.println("Data = "+data.getDate());
		System.out.println("Dia = "+ data.getDay());
		System.out.println("Mês = "+data.getMonth());
		System.out.println("Ano = "+data.getYear());
		System.out.println("Hora = "+data.getHours());
		System.out.println("Minutos = "+data.getMinutes());
		System.out.println("Segundos = "+data.getSeconds());
		System.out.println("TimeStamp = "+data.getTime());
		System.out.println("Data = "+data.toGMTString());		
	}

}
