package net.atos.api.notafiscal.service;

import javax.validation.constraints.NotNull;

import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;

public interface CriaNotaFiscal {
	
	public NotaFiscalVO persistir(@NotNull(message = "Nota Fiscal não pode ser null") NotaFiscalVO notaFiscal); 

	public boolean isType(OperacaoFiscalEnum type);
}
