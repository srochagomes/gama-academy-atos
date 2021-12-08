package net.atos.api.logistica.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaFiscalVO {

	private Long id;
	
	private LocalDate dataEmissao;
	
	
	private BigDecimal valor;
		
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
