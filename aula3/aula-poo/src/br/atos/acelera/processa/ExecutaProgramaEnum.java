package br.atos.acelera.processa;

import java.util.stream.Stream;

import br.atos.acelera.pagtos.FormaPagamento;

public class ExecutaProgramaEnum {
	
	public static void main(String[] args) {
		
		
		FormaPagamento formaPagto = FormaPagamento.DINHEIRO;
		
		System.out.println(formaPagto.getDescricao());
		
		Stream.of(FormaPagamento.values())
		 		.map(FormaPagamento::getDescricao)
		 		.forEach(System.out::println);
		
		
	}

}
