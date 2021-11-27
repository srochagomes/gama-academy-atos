package net.atos.api.notafiscal.factory;

import java.util.concurrent.atomic.AtomicInteger;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.repository.entity.ItemEntity;
import net.atos.api.notafiscal.repository.entity.ItemPK;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;
import net.atos.api.notafiscal.repository.entity.NotaFiscalVendaEntity;

public class NotaFiscalVendaFactory{
	
	private NotaFiscalVO vo;
	private NotaFiscalVendaEntity entity;
	
	public NotaFiscalVendaFactory(NotaFiscalVO pVo) {
		this.entity = this.transformaEntity(pVo);
		this.vo = pVo;		
	}
	
	public NotaFiscalVendaFactory(NotaFiscalVendaEntity pEntity) {
		this.entity = pEntity;
		this.vo = this.transformaVO(pEntity);		
	}
	
	
	private NotaFiscalVO transformaVO(NotaFiscalVendaEntity pEntity) {
		NotaFiscalVO nfVO = new NotaFiscalVO();
		nfVO.setDataEmissao(pEntity.getDataEmissao());
		nfVO.setDataLancamento(pEntity.getDataLancamento());
		nfVO.setDocumento(pEntity.getDocumento());
		nfVO.setValor(pEntity.getValor());
		nfVO.setOperacaoFiscal(pEntity.getOperacaoFiscal());
		nfVO.setCancelada(pEntity.getCancelada());
		AtomicInteger numeroItem = new AtomicInteger(); 
		pEntity.getItens().stream().forEach(item -> 
				this.construirItemVO(nfVO, numeroItem, item));
		
		return nfVO;
	}

	private void construirItemVO(NotaFiscalVO nfVO, AtomicInteger numeroItem, ItemEntity item) {
		ItemVO itemVO = new ItemVO();
		itemVO.setCodigoProduto(item.getCodigoProduto());
		itemVO.setNcm(item.getNcm());
		itemVO.setValor(item.getValor());
		
		nfVO.add(itemVO);
	}

	private NotaFiscalVendaEntity transformaEntity(NotaFiscalVO notaFiscal) {
		NotaFiscalVendaEntity nfEntity = new NotaFiscalVendaEntity();
		nfEntity.setDataEmissao(notaFiscal.getDataEmissao());
		nfEntity.setDataLancamento(notaFiscal.getDataLancamento());
		nfEntity.setDocumento(notaFiscal.getDocumento());
		nfEntity.setValor(notaFiscal.getValor());
		
		nfEntity.setCancelada(notaFiscal.getCancelada());
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
		itemEntity.setCodigoProduto(item.getCodigoProduto());
		itemEntity.setNcm(item.getNcm());
		itemEntity.setValor(item.getValor());
		
		nfEntity.add(itemEntity);
		
	}

	public NotaFiscalVendaEntity toEntity() {		
		return this.entity;
	}

	public NotaFiscalVO toVO() {
		
		return this.vo;
	}

	
	

}
