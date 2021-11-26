package net.atos.api.notafiscal.factory;

import java.util.concurrent.atomic.AtomicInteger;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.repository.entity.ItemEntity;
import net.atos.api.notafiscal.repository.entity.ItemPK;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

public class NotaFiscalFactory {
	
	private NotaFiscalVO vo;
	private NotaFiscalEntity entity;
	
	public NotaFiscalFactory(NotaFiscalVO vo) {
		this.entity = this.transformaEntity(vo);
		
	}
	
	public NotaFiscalFactory(NotaFiscalEntity entity) {
		throw new IllegalAccessError();		
	}
	
	
	private NotaFiscalEntity transformaEntity(NotaFiscalVO notaFiscal) {
		NotaFiscalEntity nfEntity = new NotaFiscalEntity();
		nfEntity.setDataEmissao(notaFiscal.getDataEmissao());
		nfEntity.setDataLancamento(notaFiscal.getDataLancamento());
		nfEntity.setDocumento(notaFiscal.getDocumento());
		nfEntity.setOperacaoFiscal(notaFiscal.getOperacaoFiscal());
		AtomicInteger numeroItem = new AtomicInteger(); 
		notaFiscal.getItens().stream().forEach(item -> 
				this.construirItemEntity(nfEntity, numeroItem, item));
		
		return nfEntity;
		
	}

	private void construirItemEntity(NotaFiscalEntity nfEntity, AtomicInteger numeroItem, ItemVO item) {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setId(new ItemPK());
		itemEntity.getId().setNumeroItem(numeroItem.incrementAndGet());
		itemEntity.getId().setNotaFiscal(nfEntity);
		itemEntity.setNcm(item.getNcm());
		itemEntity.setValor(item.getValor());
		
		nfEntity.add(itemEntity);
		
	}

	public NotaFiscalEntity toEntity() {		
		return this.entity;
	}

	
	

}
