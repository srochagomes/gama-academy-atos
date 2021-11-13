package br.atos.acelera.processa;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExecutaProgramaCalendar {

	public static void main(String[] args) {
		
		Calendar data = Calendar.getInstance();
		
		System.out.println(data.getTime());
		
		
		data.roll(Calendar.DAY_OF_MONTH, 20);
		
		System.out.println(data.getTime());
		
		data.add(Calendar.DAY_OF_MONTH, 30);
		
		System.out.println(data.getTime());		
		
	}
}
