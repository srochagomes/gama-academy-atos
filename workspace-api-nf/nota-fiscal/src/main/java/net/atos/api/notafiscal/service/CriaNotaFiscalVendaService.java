package net.atos.api.notafiscal.service;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.springframework.stereotype.Service;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.factory.NotaFiscalFactory;
import net.atos.api.notafiscal.factory.NotaFiscalVendaFactory;
import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@Service
public class CriaNotaFiscalVendaService {	
	
	private Validator validator;
	
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	
	public CriaNotaFiscalVendaService(Validator v, NotaFiscalVendaRepository repository) {
		this.validator = v;		
		this.notaFiscalRepositoy = repository; 	
	}

	public NotaFiscalVO persistir(@NotNull(message = "Nota Fiscal não pode ser null") NotaFiscalVO notaFiscal) {		
		
		
			
		Set<ConstraintViolation<NotaFiscalVO>> 
					validateMessages = this.validator.validate(notaFiscal);
		
		if (!validateMessages.isEmpty()) {
			throw new ConstraintViolationException("Nota Fiscal Inválida",validateMessages);
		}
		
		
		if (!notaFiscal.getDataEmissao().isEqual(LocalDate.now())) {
			throw new BadRequestException("A data de emissão deve ser atual.");			
		}
		
		NotaFiscalVendaEntity nfEntity = new NotaFiscalVendaFactory(notaFiscal)
													.toEntity();				
		
		notaFiscalRepositoy.save(nfEntity);		
		
		notaFiscal.setId(nfEntity.getId());	
		
		return notaFiscal; 
		
	}
	
		
	public NotaFiscalVendaEntity recuperaPorId(long id) {
		return this.notaFiscalRepositoy.findById(id)
				.orElseThrow(()-> new NotFoundException("Não encontrada a nf de venda com id = "+id));		
	}
	
}
