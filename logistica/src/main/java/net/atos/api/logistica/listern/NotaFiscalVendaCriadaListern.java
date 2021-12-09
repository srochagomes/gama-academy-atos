package net.atos.api.logistica.listern;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import net.atos.api.logistica.domain.NotaFiscalVO;
import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.service.CriaOrdemService;

@Component
public class NotaFiscalVendaCriadaListern {
	
	private CriaOrdemService criaOrdemService;
	
	public NotaFiscalVendaCriadaListern(CriaOrdemService pCriaOrdemService) {
		this.criaOrdemService = pCriaOrdemService;
	}
	
	@RabbitListener(queues = "cria-ordem-servico")
	public void execute(NotaFiscalVO notaFiscal) {
		
		OrdemServicoVO orderServico = new OrdemServicoVO();
		orderServico.setIdNotaFiscal(notaFiscal.getId());
		orderServico.setDataEmissao(notaFiscal.getDataEmissao());
		orderServico.setValor(notaFiscal.getValor());
		
		this.criaOrdemService.processar(orderServico);
		
	}

}
