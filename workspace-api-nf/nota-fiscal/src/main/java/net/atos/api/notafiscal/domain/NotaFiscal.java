package net.atos.api.notafiscal.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaFiscal {

	private LocalDate dataEmissao;
	
	private LocalDateTime dataLancamento;
	
	private OperacaoFiscal operacaoFiscal;
	
	private BigDecimal valor;
	
	private String documento;
	
	private List<Item> itens;
	
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public OperacaoFiscal getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscal operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void add(Item item) {
		List<Item> itensLocal = 
				Optional.ofNullable(this.getItens()).orElseGet(()->new ArrayList());		
		itensLocal.add(item);
		
		this.itens = itensLocal; 
	}

	
}
