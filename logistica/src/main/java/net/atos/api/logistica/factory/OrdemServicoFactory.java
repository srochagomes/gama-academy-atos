package net.atos.api.logistica.factory;

import net.atos.api.logistica.domain.OrdemServicoVO;
import net.atos.api.logistica.repository.entity.OrdemServicoEntity;

public class OrdemServicoFactory {
	
		private OrdemServicoVO  vo;
		private OrdemServicoEntity entity;
		
		public OrdemServicoFactory(OrdemServicoVO pVo) {
			this.entity = this.transformaEntity(pVo);
			this.vo = pVo;		
		}
		
		public OrdemServicoFactory(OrdemServicoEntity pEntity) {
			this.entity = pEntity;
			this.vo = this.transformaVO(pEntity);		
		}
		
		
		private OrdemServicoVO transformaVO(OrdemServicoEntity pEntity) {
			OrdemServicoVO vo = new OrdemServicoVO();
			vo.setId(pEntity.getId());
			vo.setIdNotaFiscal(pEntity.getIdNotaFiscal());
			vo.setDataEmissao(pEntity.getDataEmissao());
			vo.setValor(pEntity.getValor());
			
			return vo;
		}


		private OrdemServicoEntity transformaEntity(OrdemServicoVO vo) {
			OrdemServicoEntity entity = new OrdemServicoEntity();
			entity.setId(vo.getId());
			entity.setIdNotaFiscal(vo.getIdNotaFiscal());
			entity.setDataEmissao(vo.getDataEmissao());
			entity.setValor(vo.getValor());		
			return entity;
			
		}


		public OrdemServicoEntity toEntity() {		
			return this.entity;
		}

		public OrdemServicoVO toVO() {
			
			return this.vo;
		}

	

}
