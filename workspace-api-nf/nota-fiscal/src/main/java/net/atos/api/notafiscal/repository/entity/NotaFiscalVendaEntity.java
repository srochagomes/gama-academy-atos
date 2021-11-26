package net.atos.api.notafiscal.repository.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;

@Entity
@DiscriminatorValue("VENDA")
public class NotaFiscalVendaEntity extends NotaFiscalEntity{

	
	@Column(name = "FL_CANCELADA")
	private Boolean cancelada;
	
	@Override
	public void setOperacaoFiscal(OperacaoFiscalEnum operacaoFiscal) {
		throw new IllegalArgumentException();
	}

	public Boolean getCancelada() {
		return cancelada;
	}
	
	public Boolean isCancelada() {
		return Optional.ofNullable(this.cancelada).orElseGet(()->Boolean.FALSE);
	}


	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}
	
}
