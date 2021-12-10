package net.atos.api.notafiscal.events;

import net.atos.api.notafiscal.domain.NotaFiscalVO;

public class NotaFiscalVendaCanceledEvent {
	
	private NotaFiscalVO notaFiscal;
	
	public NotaFiscalVendaCanceledEvent(NotaFiscalVO pNotaFiscal) {
		this.notaFiscal = pNotaFiscal;		
	}

	public NotaFiscalVO getNotaFiscal() {
		return notaFiscal;
	}
	
	

}
