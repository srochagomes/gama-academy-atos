package net.atos.api.notafiscal.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "TB_NOTA_FISCAL_ITEM")
public class ItemEntity implements Serializable{

	
	/**
	 * SERIAL UID
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPK id;
	
	@Column(name = "CD_PRODUTO")
	@NotNull(message = "Campo Codigo produto não pode ser nulo")
	private Integer codigoProduto;

	@Column(name = "CD_NCM")
	@NotNull(message="Campo NCM não pode ser nulo")
	private String ncm;
	
	@Column(name = "VL_ITEM")
	@NotNull(message = "Campo Valor do Item não pode ser nulo")	
	@Positive
	private BigDecimal valor;
	
	
	
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public ItemPK getId() {
		return id;
	}
	public void setId(ItemPK id) {
		this.id = id;
	}
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	
	

}
