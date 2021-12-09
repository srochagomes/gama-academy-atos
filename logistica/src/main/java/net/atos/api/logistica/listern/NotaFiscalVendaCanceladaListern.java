package net.atos.api.logistica.listern;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import net.atos.api.logistica.domain.CancelaOrdemServicoVO;
import net.atos.api.logistica.domain.NotaFiscalVO;
import net.atos.api.logistica.service.CancelaOrdemService;

public class NotaFiscalVendaCanceladaListern {
	
	private CancelaOrdemService cancelaOrdemService;
	
	public NotaFiscalVendaCanceladaListern(CancelaOrdemService pCancelaOrdemService) {
		this.cancelaOrdemService = pCancelaOrdemService;		
	}
	
	@RabbitListener(queues = "cancela-ordem-servico")
	public void execute(NotaFiscalVO notaFiscal) {
		
		CancelaOrdemServicoVO cancelaOrdemServico = new CancelaOrdemServicoVO();
		cancelaOrdemServico.setIdNotaFiscal(notaFiscal.getId());
		
		this.cancelaOrdemService.processar(cancelaOrdemServico);	
		
	}

}
