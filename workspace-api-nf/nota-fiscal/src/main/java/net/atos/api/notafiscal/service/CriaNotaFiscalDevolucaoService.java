package net.atos.api.notafiscal.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;
import net.atos.api.notafiscal.factory.NotaFiscalDevolucaoFactory;
import net.atos.api.notafiscal.repository.NotaFiscalDevolucaoRepository;
import net.atos.api.notafiscal.repository.entity.NotaFiscalDevolucaoEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

@Service
public class CriaNotaFiscalDevolucaoService implements CriaNotaFiscal{

	private Validator validator;
	private NotaFiscalDevolucaoRepository notaFiscalRepositoy;
	private BuscaNotaFiscalVendaService buscaNotaFiscalVendaService;


	public CriaNotaFiscalDevolucaoService(Validator v, 
			NotaFiscalDevolucaoRepository nfRepositoy,
			BuscaNotaFiscalVendaService bNFVendaService) {
		this.validator = v;
		this.notaFiscalRepositoy= nfRepositoy;
		this.buscaNotaFiscalVendaService = bNFVendaService;
	}

	@Transactional
	public NotaFiscalVO persistir(@NotNull NotaFiscalVO notaFiscal) {
		Set<ConstraintViolation<NotaFiscalVO>> validateMessages = this.validator.validate(notaFiscal);

		if (!validateMessages.isEmpty()) {
			throw new ConstraintViolationException("Nota Fiscal Inválida", validateMessages);
		}
		
		if (!notaFiscal.getDataEmissao().isEqual(LocalDate.now())) {
			throw new BadRequestException("A data de emissão deve ser atual.");			
		}
		
		
		Optional.ofNullable(notaFiscal.getIdNotaFiscalVenda())
					.orElseThrow(()-> new BadRequestException("Identificador da nota fiscal de venda é obrigatório."));
		
		NotaFiscalVendaEntity notaVendaEncontrada = buscaNotaFiscalVendaService.porId(notaFiscal.getIdNotaFiscalVenda());
		
		if (notaVendaEncontrada.isCancelada()) {
			throw new BadRequestException("Operação invalida, devido a Nota Fiscal de Venda estar cancelada.");
		}
		
		Optional<NotaFiscalDevolucaoEntity> findByIdNotaFiscalVenda = this.notaFiscalRepositoy.findByIdNotaFiscalVenda(notaVendaEncontrada.getId());
		
		if (findByIdNotaFiscalVenda.isPresent()) {
			throw new BadRequestException("Operação invalida, devido a Nota Fiscal de Venda já possuir uma devolução.");	
		}
		
		NotaFiscalDevolucaoEntity nfEntity = new NotaFiscalDevolucaoFactory(notaFiscal)
										.toEntity();
		
		this.notaFiscalRepositoy.save(nfEntity);
		
		notaFiscal.setId(nfEntity.getId());
		
		return notaFiscal;
	}

	@Override
	public boolean isType(OperacaoFiscalEnum type) {		
		return OperacaoFiscalEnum.DEVOLUCAO.equals(type);
	}

}
