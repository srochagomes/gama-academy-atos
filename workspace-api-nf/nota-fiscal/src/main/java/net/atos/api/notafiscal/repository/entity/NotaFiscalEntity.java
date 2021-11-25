package net.atos.api.notafiscal.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;

@Entity
@Table(name = "TB_NOTA_FISCAL")
public class NotaFiscalEntity {
	
	@Id
	@Column(name = "ID_NOTA_FISCAL")
	private Long id;	

	@Column(name = "DT_EMISSAO")
	@NotNull(message = "Campo data de emissão não pode ser nula")	
	private LocalDate dataEmissao;
		
	@Column(name = "DT_LANCAMENTO")
	@NotNull(message = "Campo data de Lançamento não pode ser nula")
	private LocalDateTime dataLancamento;
	
	
	@Column(name = "OP_FISCAL")
	@NotNull(message = "Campo Operacao Fiscal não pode ser nulo")
	@Enumerated(EnumType.STRING)
	private OperacaoFiscalEnum operacaoFiscal;
	
	@Column(name = "VL_NF")
	@NotNull(message="Campo Valor não pode ser nulo")
	@Positive
	private BigDecimal valor;
		
	@Column(name = "VL_NF")
	@NotNull(message = "Campo Documento não pode ser nulo")
	private String documento;
	
	
	@NotNull(message = "Campo Itens deve ter pelo menos um item")
	@Size(min = 1, message = "Campo Itens deve ter pelo menos um item")
	@Valid
	@OneToMany(mappedBy = "id.notaFiscal")	
	private List<ItemEntity> itens;
	
	
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

	public List<ItemEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemEntity> itens) {
		this.itens = itens;
	}

	public void add(ItemEntity item) {
		List<ItemEntity> itensLocal = 
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
