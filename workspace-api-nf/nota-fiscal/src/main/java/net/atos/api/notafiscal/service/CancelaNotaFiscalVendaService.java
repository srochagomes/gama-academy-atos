package net.atos.api.notafiscal.service;

import java.util.Optional;

import javax.validation.Validator;
import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	public CancelaNotaFiscalVendaService(Validator pValidator, 
			NotaFiscalVendaRepository pNotaFiscalRepositoy,
			BuscaNotaFiscalVendaService pBuscaNotaFiscalVendaService, 
			NotaFiscalDevolucaoRepository pnotaFiscalDevolucaoRepository) {
		
		this.validator = pValidator;
		this.notaFiscalRepositoy = pNotaFiscalRepositoy;
		this.buscaNotaFiscalVendaService = pBuscaNotaFiscalVendaService;
		this.notaFiscalDevolucaoRepository = pnotaFiscalDevolucaoRepository;
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
		notaFiscalRepositoy.save(notaFiscalVendaEncontrada);		
		
		
		
	}

}
