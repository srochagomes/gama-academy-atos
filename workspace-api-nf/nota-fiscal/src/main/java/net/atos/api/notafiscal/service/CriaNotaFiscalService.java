package net.atos.api.notafiscal.service;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.repository.NotaFiscalRepository;
import net.atos.api.notafiscal.repository.entity.ItemEntity;
import net.atos.api.notafiscal.repository.entity.ItemPK;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

@Service
public class CriaNotaFiscalService {	
	
	private Validator validator;
	
	private NotaFiscalRepository notaFiscalRepositoy;
	
	public CriaNotaFiscalService(Validator v, NotaFiscalRepository repository) {
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
		
		NotaFiscalEntity nfEntity = new NotaFiscalEntity();
		nfEntity.setDataEmissao(notaFiscal.getDataEmissao());
		nfEntity.setDataLancamento(notaFiscal.getDataLancamento());
		nfEntity.setDocumento(notaFiscal.getDocumento());
		nfEntity.setOperacaoFiscal(notaFiscal.getOperacaoFiscal());
		AtomicInteger numeroItem = new AtomicInteger(); 
		notaFiscal.getItens().stream().forEach(item -> 
				this.construirItem(nfEntity, numeroItem, item));
		
		
		notaFiscalRepositoy.save(nfEntity);		
		
		notaFiscal.setId(nfEntity.getId());	
		
		return notaFiscal; 
		
	}

	private void construirItem(NotaFiscalEntity nfEntity, AtomicInteger numeroItem, ItemVO item) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(new ItemPK());
		itemEntity.getId().setNumeroItem(numeroItem.incrementAndGet());
		itemEntity.getId().setNotaFiscal(nfEntity);
		itemEntity.setNcm(item.getNcm());
		itemEntity.setValor(item.getValor());
		
		nfEntity.add(itemEntity);
		
	}
	
	
	
	
	
}
