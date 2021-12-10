package net.atos.api.notafiscal.events;

import net.atos.api.notafiscal.domain.NotaFiscalVO;

public class NotaFiscalVendaCreatedEvent {
	
	private NotaFiscalVO notaFiscal;
	
	public NotaFiscalVendaCreatedEvent(NotaFiscalVO pNotaFiscal) {
		this.notaFiscal = pNotaFiscal;		
	}

	public NotaFiscalVO getNotaFiscal() {
		return notaFiscal;
	}
	
	

}
