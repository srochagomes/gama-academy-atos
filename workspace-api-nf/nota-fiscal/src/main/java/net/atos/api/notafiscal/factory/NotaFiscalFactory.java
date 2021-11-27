package net.atos.api.notafiscal.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import net.atos.api.notafiscal.domain.ItemVO;
import net.atos.api.notafiscal.domain.NotaFiscalVO;
import net.atos.api.notafiscal.repository.entity.ItemEntity;
import net.atos.api.notafiscal.repository.entity.ItemPK;
import net.atos.api.notafiscal.repository.entity.NotaFiscalEntity;

public class NotaFiscalFactory {
	
		private NotaFiscalVO vo;
		private NotaFiscalEntity entity;
		
		public NotaFiscalFactory(NotaFiscalVO pVo) {
			this.entity = this.transformaEntity(pVo);
			this.vo = pVo;		
		}
		
		public NotaFiscalFactory(NotaFiscalEntity pEntity) {
			this.entity = pEntity;
			this.vo = this.transformaVO(pEntity);		
		}
		
		
		private NotaFiscalVO transformaVO(NotaFiscalEntity pEntity) {
			NotaFiscalVO nfVO = new NotaFiscalVO();
			nfVO.setId(pEntity.getId());
			nfVO.setDataEmissao(pEntity.getDataEmissao());
			nfVO.setDataLancamento(pEntity.getDataLancamento());
			nfVO.setDocumento(pEntity.getDocumento());
			nfVO.setValor(pEntity.getValor());
			nfVO.setOperacaoFiscal(pEntity.getOperacaoFiscal());
			
			AtomicInteger numeroItem = new AtomicInteger();
			List<ItemEntity> itens = Optional.ofNullable(pEntity.getItens()).orElseGet(ArrayList::new);
			itens.stream().forEach(item -> 
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

		private NotaFiscalEntity transformaEntity(NotaFiscalVO notaFiscal) {
			NotaFiscalEntity nfEntity = new NotaFiscalEntity();
			nfEntity.setId(notaFiscal.getId());
			nfEntity.setDataEmissao(notaFiscal.getDataEmissao());
			nfEntity.setDataLancamento(notaFiscal.getDataLancamento());
			nfEntity.setDocumento(notaFiscal.getDocumento());
			nfEntity.setOperacaoFiscal(notaFiscal.getOperacaoFiscal());
			nfEntity.setValor(notaFiscal.getValor());
			AtomicInteger numeroItem = new AtomicInteger();
			List<ItemVO> itens = Optional.ofNullable(notaFiscal.getItens()).orElseGet(ArrayList::new);
			itens.stream().forEach(item -> 
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

		public NotaFiscalEntity toEntity() {		
			return this.entity;
		}

		public NotaFiscalVO toVO() {
			
			return this.vo;
		}

	

}
