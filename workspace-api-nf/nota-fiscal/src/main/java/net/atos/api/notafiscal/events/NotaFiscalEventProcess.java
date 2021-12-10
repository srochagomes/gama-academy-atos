package net.atos.api.notafiscal.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import net.atos.api.notafiscal.domain.NotaFiscalVO;

@Service
public class NotaFiscalEventProcess {
	
	
	private RabbitTemplate rabbitTemplate;

	public NotaFiscalEventProcess(RabbitTemplate pRabbitTemplate) {
		this.rabbitTemplate = pRabbitTemplate;
		
	}
	

	@Async
	@TransactionalEventListener
	public void handleEvent(NotaFiscalVendaCreatedEvent event) {
		
		NotaFiscalVO notaFiscal = event.getNotaFiscal();
		
		this.rabbitTemplate.convertAndSend("nota-fiscal", 
				"nf.created.venda", notaFiscal);
	}
	
	@Async
	@TransactionalEventListener
	public void handleEvent(NotaFiscalVendaCanceledEvent event) {
		
		NotaFiscalVO notaFiscal = event.getNotaFiscal();
		
		this.rabbitTemplate.convertAndSend("nota-fiscal", 
				"nf.canceled.venda", notaFiscal);
	}

}
