package net.atos.api.notafiscal.repository.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.atos.api.notafiscal.domain.OperacaoFiscalEnum;

@Entity
@DiscriminatorValue("DEVOLUCAO")
public class NotaFiscalDevolucaoEntity extends NotaFiscalEntity{

	

	public NotaFiscalDevolucaoEntity() {
		super.setOperacaoFiscal(OperacaoFiscalEnum.DEVOLUCAO);
	}
	
	@Column(name = "ID_NOTA_FISCAL_VENDA")
	private Long idNotaFiscalVenda;	

	
	@Override	
	public void setOperacaoFiscal(OperacaoFiscalEnum operacaoFiscal) {
		throw new IllegalArgumentException();
	}


	public Long getIdNotaFiscalVenda() {
		return idNotaFiscalVenda;
	}


	public void setIdNotaFiscalVenda(Long idNotaFiscalVenda) {
		this.idNotaFiscalVenda = idNotaFiscalVenda;
	}


}
