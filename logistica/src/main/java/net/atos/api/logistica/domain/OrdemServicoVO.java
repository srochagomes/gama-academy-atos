package net.atos.api.logistica.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


public class OrdemServicoVO {	
	
	
	private Long id;
	
	@NotNull(message = "Campo id nota fiscal não pode ser null")
	private Long idNotaFiscal;
	
	@NotNull(message = "Campo valor não pode ser null")
	@Min(value = 1l)
	private BigDecimal valor;
	
	@NotNull(message = "Campo data de emissão não pode ser nula")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataEmissao;

	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNotaFiscal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServicoVO other = (OrdemServicoVO) obj;
		return Objects.equals(idNotaFiscal, other.idNotaFiscal);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
