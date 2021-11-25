package net.atos.api.notafiscal.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemVO {
	
	@NotNull(message = "Campo Codigo produto não pode ser nulo")
	private Integer codigoProduto;
	
	@NotNull(message="Campo NCM não pode ser nulo")
	private String ncm;
	
	@NotNull(message = "Campo Valor do Item não pode ser nulo")	
	@Positive
	private BigDecimal valor;
	
	
	
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
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
	
	
	

}
