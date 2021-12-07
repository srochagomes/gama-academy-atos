package net.atos.api.logistica.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_ORDEM_SERVICO", 
    uniqueConstraints =  @UniqueConstraint(name = "ID_NOTA_FISCAL_UNIQUE",columnNames = {"ID_NOTA_FISCAL"}))
public class OrdemServicoEntity {
	
	@Id
	@Column(name = "ID_ORDEM_SERVICO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_ordem_servico")
	@SequenceGenerator(name = "sq_ordem_servico", sequenceName = "sequence_ordem_servico",allocationSize = 1,initialValue = 1)
	private Long id;
	
	
	@Column(name = "ID_NOTA_FISCAL")
	private Long idNotaFiscal;
	
	@Column(name = "DT_EMISSAO")
	private LocalDate dataEmissao;
	
	@Column(name = "DT_EVENTO")
	private LocalDateTime dataEvento;

	@Column(name = "VL_ORDEM_SERVICO")
	private BigDecimal valor;

	@Column(name = "FL_CANCELADO")
	private Boolean cancelado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDateTime getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	

}
