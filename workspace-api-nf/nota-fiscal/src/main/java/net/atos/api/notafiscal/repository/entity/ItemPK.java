package net.atos.api.notafiscal.repository.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ItemPK {
		
	@Column(name = "NR_ITEM")
	@NotNull(message = "Campo numero do Item não pode ser nulo")
	private Integer numeroItem;
	
	@ManyToOne
	private NotaFiscalEntity notaFiscal;

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}

	public NotaFiscalEntity getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscalEntity notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}
