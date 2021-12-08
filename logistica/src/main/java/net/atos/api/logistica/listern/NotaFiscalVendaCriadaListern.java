package net.atos.api.logistica.listern;

import net.atos.api.logistica.domain.NotaFiscalVO;
import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.service.CriaOrdemService;

public class NotaFiscalVendaCriadaListern {
	
	private CriaOrdemService criaOrdemService;
	
	public NotaFiscalVendaCriadaListern(CriaOrdemService pCriaOrdemService) {
		this.criaOrdemService = pCriaOrdemService;
	}
	
	public void execute(NotaFiscalVO notaFiscal) {
		
		OrdemServicoVO orderServico = new OrdemServicoVO();
		orderServico.setIdNotaFiscal(notaFiscal.getId());
		orderServico.setDataEmissao(notaFiscal.getDataEmissao());
		orderServico.setValor(notaFiscal.getValor());
		
		this.criaOrdemService.processar(orderServico);
		
	}

}
