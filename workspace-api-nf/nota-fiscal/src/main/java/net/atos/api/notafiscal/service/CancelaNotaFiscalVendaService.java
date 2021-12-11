package net.atos.api.notafiscal.service;

import java.util.Optional;

import javax.validation.Validator;
import javax.ws.rs.BadRequestException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.events.NotaFiscalVendaCanceledEvent;
import net.atos.api.notafiscal.repository.NotaFiscalDevolucaoRepository;
import net.atos.api.notafiscal.repository.NotaFiscalVendaRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalDevolucaoEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@Service
public class CancelaNotaFiscalVendaService {

	private Validator validator;
	private BuscaNotaFiscalVendaService buscaNotaFiscalVendaService;
	private NotaFiscalVendaRepository notaFiscalRepositoy;
	private NotaFiscalDevolucaoRepository notaFiscalDevolucaoRepository;
	private ApplicationEventPublisher eventPublisher;


	public CancelaNotaFiscalVendaService(Validator pValidator, 
			NotaFiscalVendaRepository pNotaFiscalRepositoy,
			BuscaNotaFiscalVendaService pBuscaNotaFiscalVendaService, 
			NotaFiscalDevolucaoRepository pnotaFiscalDevolucaoRepository,
			ApplicationEventPublisher pEventPublisher) {
		
		this.validator = pValidator;
		this.notaFiscalRepositoy = pNotaFiscalRepositoy;
		this.buscaNotaFiscalVendaService = pBuscaNotaFiscalVendaService;
		this.notaFiscalDevolucaoRepository = pnotaFiscalDevolucaoRepository;
		this.eventPublisher = pEventPublisher;
	}

	@Transactional
	public void cancelar(Long id) {
		
		NotaFiscalVendaEntity notaFiscalVendaEncontrada = 
				    this.buscaNotaFiscalVendaService.porId(id);
		
		Optional<NotaFiscalDevolucaoEntity> notaFiscalDevolucaoEncontrada = this.notaFiscalDevolucaoRepository
		     .findByIdNotaFiscalVenda(notaFiscalVendaEncontrada.getId());
		
		if (notaFiscalDevolucaoEncontrada.isPresent()) {
			throw new BadRequestException("Nota fiscal de venda não pode ser cancelada, pois existe uma nota fiscal de devolução associada a ela.");
		}
		
		if (notaFiscalVendaEncontrada.ehSuperior24Horas()) {
			throw new BadRequestException("Nota fiscal de venda não pode ser cancelada, passou o período de 24hrs.");	
		}
		
		notaFiscalVendaEncontrada.setCancelada(Boolean.TRUE);
		
		NotaFiscalVO notaFiscalVO = new NotaFiscalVO(); 
		notaFiscalVO.setId(id);
		NotaFiscalVendaCanceledEvent event = new NotaFiscalVendaCanceledEvent(notaFiscalVO);  
		
		this.eventPublisher.publishEvent(event);
		
	}

}
