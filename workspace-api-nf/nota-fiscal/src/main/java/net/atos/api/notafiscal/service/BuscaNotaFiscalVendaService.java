package net.atos.api.notafiscal.service;

import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;

import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@Service
public class BuscaNotaFiscalVendaService {	
	
	private Validator validator;
	
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	
	public BuscaNotaFiscalVendaService(Validator v, NotaFiscalVendaRepository repository) {
		this.validator = v;		
		this.notaFiscalRepositoy = repository; 	
	}

		
	public NotaFiscalVendaEntity porId(long id) {
		return this.notaFiscalRepositoy.findById(id)
				.orElseThrow(()-> new NotFoundException("Não encontrada a nf de venda com id = "+id));		
	}
	
}
