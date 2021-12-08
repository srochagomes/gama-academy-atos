package net.atos.api.logistica.domain;

import java.util.Objects;

import javax.validation.constraints.NotNull;


public class CancelaOrdemServicoVO {	
	
	@NotNull(message = "Campo id nota fiscal não pode ser null")
	private Long idNotaFiscal;
	
	
	public Long getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public void setIdNotaFiscal(Long idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
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
		return Objects.equals(idNotaFiscal, other.getIdNotaFiscal());
	}


}
