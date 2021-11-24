package net.atos.api.notafiscal.service;

import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import net.atos.api.notafiscal.domain.Item;
import net.atos.api.notafiscal.domain.NotaFiscal;

@Service
public class CriaNotaFiscal {

	public void persistir(NotaFiscal notaFiscal) {
		
		if (ObjectUtils.isEmpty(notaFiscal.getDataEmissao())) {
			throw new BadRequestException("Campo data de emissão não pode ser nula");
		}
	
		Optional.ofNullable(notaFiscal.getDataLancamento())
		.orElseThrow(()-> new BadRequestException("Campo data de Lançamento não pode ser nula"));
		
		Optional.ofNullable(notaFiscal.getOperacaoFiscal())
		.orElseThrow(()-> new BadRequestException("Campo Operacao Fiscal não pode ser nulo"));
		
		Optional.ofNullable(notaFiscal.getValor())
		.orElseThrow(()-> new BadRequestException("Campo Valor não pode ser nulo"));
		
		Optional.ofNullable(notaFiscal.getDocumento())
		.orElseThrow(()-> new BadRequestException("Campo Documento não pode ser nulo"));

		if (ObjectUtils.isEmpty(notaFiscal.getItens()) ||
				notaFiscal.getItens().size() < 1) {
			throw new BadRequestException("Campo Itens deve ter pelo menos um item");			
		}
		
		
		notaFiscal.getItens().forEach(item -> this.validaItem(item));
		
		
		
		
		
		
		
	}
	
	private void validaItem(Item item) {
		Optional.ofNullable(item.getCodigoProduto())
		.orElseThrow(()-> new BadRequestException("Campo Codigo produto não pode ser nulo"));

		Optional.ofNullable(item.getNcm())
		.orElseThrow(()-> new BadRequestException("Campo NCM não pode ser nulo"));
		
		Optional.ofNullable(item.getValor())
		.orElseThrow(()-> new BadRequestException("Campo Valor do Item não pode ser nulo"));
		
	}


	
	
	
	
}
