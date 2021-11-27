package net.atos.api.notafiscal.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ItemPK implements Serializable{
		
	/**
	 * SERIAL UID
	 */
	private static final long serialVersionUID = 5240150106898767721L;

	@Column(name = "NR_ITEM")
	@NotNull(message = "Campo numero do Item não pode ser nulo")
	private Integer numeroItem;
	
	@ManyToOne
	@JoinColumn(name="ID_NOTA_FISCAL")
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
