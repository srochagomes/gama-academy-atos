package net.atos.api.notafiscal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.notafiscal.repository.entity.NotaFiscalDevolucaoEntity;

@Repository
public interface NotaFiscalDevolucaoRepository extends CrudRepository<NotaFiscalDevolucaoEntity, Long>{
	
	public Optional<NotaFiscalDevolucaoEntity> findByIdNotaFiscalVenda(Long idVenda);

}
