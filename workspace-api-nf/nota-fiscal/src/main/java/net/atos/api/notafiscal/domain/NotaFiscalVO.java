package net.atos.api.notafiscal.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class NotaFiscalVO {

	private Long id;
	
	@NotNull(message = "Campo data de emissão não pode ser nula")
	private LocalDate dataEmissao;
	
	@NotNull(message = "Campo data de Lançamento não pode ser nula")
	private LocalDateTime dataLancamento;
	
	
	@NotNull(message = "Campo Operacao Fiscal não pode ser nulo")
	private OperacaoFiscalEnum operacaoFiscal;
	
	@NotNull(message="Campo Valor não pode ser nulo")
	@Positive
	private BigDecimal valor;
		
	@NotNull(message = "Campo Documento não pode ser nulo")
	private String documento;
	
	
	@NotNull(message = "Campo Itens deve ter pelo menos um item")
	@Size(min = 1, message = "Campo Itens deve ter pelo menos um item")
	@Valid
	private List<ItemVO> itens;
	
	
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

	public OperacaoFiscalEnum getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscalEnum operacaoFiscal) {
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

	public List<ItemVO> getItens() {
		return itens;
	}

	public void setItens(List<ItemVO> itens) {
		this.itens = itens;
	}

	public void add(ItemVO item) {
		List<ItemVO> itensLocal = 
				Optional.ofNullable(this.getItens()).orElseGet(()->new ArrayList());		
		itensLocal.add(item);
		
		this.itens = itensLocal; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
